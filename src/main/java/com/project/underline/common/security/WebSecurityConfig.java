package com.project.underline.common.security;


import com.project.underline.common.jwt.JwtFilter;
import com.project.underline.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Security Config 구현 시 WebSecurityConfigurerAdapter 가 deprecated 됨에 따라(Spring Security 5.4)
     * @Configuration 사용 직접 빈으로 등록, SecurityFilterChain 사용
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.httpBasic().disable()
                .csrf().disable();
        http.headers().frameOptions().disable();
        http
                /*핸들러 config 자리
                .exceptionHandling()
                .and()
                */
                //jwt 토큰 인증이므로 세션은 사용하지 않겠다. stateless 로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // request 권한 설정
                .and()
                .authorizeRequests()
                .antMatchers("/sign-in").permitAll()
                .antMatchers(HttpMethod.POST, "/sign-up").permitAll()
                .antMatchers(HttpMethod.GET,"/category-list").permitAll()
                /* 유저 권한 나눌 경우 사용
                .antMatchers("/temp").hasRole("ADMIN")
                 */
                .anyRequest().authenticated()

                /*
                 * 직접 구현한 Jwt 관련 필터를
                 * 이전 사용하던 토이프로젝트의 JwtSecurityConfig 등록 대신 사용할 수 있는 방법
                 * addFilterBefore
                */
                .and()
                .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
