package com.project.underline.common.security;

import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    /*
     해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴 메서드
     현재 구조에선 UserDetails를 구현한 User 객체를 사용하지 않으므로 변환하는 과정 추가되어 있음
     */
    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority().toString());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getEmail()),
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
