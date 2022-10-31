package com.project.underline.user.service;

import com.project.underline.common.jwt.JwtTokenProvider;
import com.project.underline.common.jwt.TokenDto;
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

    public Long createUser(SignupRequestDto signupRequestDto) {

        /*String validatedEmail = signupRequestDto.getEmail().replaceAll(" ", "");

        if (userRepository.existsByEmail(validatedEmail)) {
            throw new RuntimeException("이미 사용 중인 이메일 입니다.");
        }*/

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

        return tokenDto;
    }

}
