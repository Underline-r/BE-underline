package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.jwt.JwtTokenProvider;
import com.project.underline.common.jwt.TokenDto;
import com.project.underline.common.jwt.TokenRequestDto;
import com.project.underline.common.jwt.refreshtoken.RefreshToken;
import com.project.underline.common.jwt.refreshtoken.RefreshTokenRepository;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.web.dto.LoginRequestDto;
import com.project.underline.user.web.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * @param signupRequestDto
     * @return
     */
    public Long createUser(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        SecurityUtil.checkValidPassword(password);
        validationEmail(email);
        User user = signupRequestDto.toUser(passwordEncoder);
        return userRepository.save(user).getId();
    }

    public TokenDto login(LoginRequestDto loginRequestDto) {
        Optional<User> findUser = userRepository.findByEmail(loginRequestDto.getEmail());
        boolean isFirst = false;
        if (findUser.isPresent()){
            if (refreshTokenRepository.findById(findUser.get().getId().toString()).isEmpty()){
                isFirst = true;
            }
        } else {
            throw new UnderlineException(ErrorCode.NO_SUCH_USER);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
        tokenDto.setInitialLogin(isFirst);

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(authentication.getName())
                .refreshValue(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto refresh(TokenRequestDto tokenRequestDto) {
        // 1. Refresh token 검사
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new UnderlineException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        // 2. db에 토큰이 담겨있는지 검사
        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(
                        () -> new UnderlineException(ErrorCode.INVALID_TOKEN)
                );

        // 3. 요청 토큰, db 토큰이 일치하는지 검사
        if (!refreshToken.getRefreshValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new UnderlineException(ErrorCode.INVALID_TOKEN);
        }

        // 4. 1,2,3 검증 단계 이후 -> 신규 토큰 생성 후 리턴
        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        tokenDto.setInitialLogin(false);

        return tokenDto;
    }

    /**
     * DB에 존재하는 유저인지 확인
     */
    public User existUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)
        );
    }

    public void validationEmail(String email) {
        if (!isValidEmail(email)) {
            throw new UnderlineException(ErrorCode.INVALID_EMAIL);
        }
        if (userRepository.existsByEmail(email)) {
            throw new UnderlineException(ErrorCode.DUP_EMAIL);
        }
    }

    public boolean isValidEmail(String email) {
        boolean ret = false;
        String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            ret = true;
        }
        return ret;
    }

}
