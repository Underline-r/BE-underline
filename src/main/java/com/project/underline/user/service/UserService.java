package com.project.underline.user.service;

import com.project.underline.common.jwt.JwtTokenProvider;
import com.project.underline.common.jwt.TokenDto;
import com.project.underline.common.jwt.TokenRequestDto;
import com.project.underline.common.jwt.refreshtoken.RefreshToken;
import com.project.underline.common.jwt.refreshtoken.RefreshTokenRepository;
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
     * TODO: 커스텀 예외 만들 지 다시 생각해봅시다.
     * @param signupRequestDto
     * @return
     */
    public Long createUser(SignupRequestDto signupRequestDto) {
        String validatedEmail = signupRequestDto.getEmail().replaceAll(" ", "");
        if (userRepository.existsByEmail(validatedEmail)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        User user = signupRequestDto.toUser(passwordEncoder);
        return userRepository.save(user).getId();
    }

    public TokenDto login(LoginRequestDto loginRequestDto) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .userEmail(authentication.getName())
                .refreshValue(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto refresh(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(
                        () -> new RuntimeException("로그아웃 한 사용자 입니다.")
                );

        if (!refreshToken.getRefreshValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return tokenDto;
    }

}
