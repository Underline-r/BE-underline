package com.project.underline.common.security;


import com.project.underline.common.exception.JwtAccessDeniedHandler;
import com.project.underline.common.exception.JwtAuthenticationEntryPoint;
import com.project.underline.common.jwt.JwtFilter;
import com.project.underline.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 아래 WebSecurityCustomizer의 Tomcat Run 중 WARN Message
     * warning : This is not recommended -- please use permitAll via HttpSecurity#authorizeHttpRequests instead.
     *
     * ->
     * web ignoring 사용하지 않고 요청 권한 byPass 시키기
     * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
     */

    /** 2
     * authorizeHttpRequests vs authorizedRequests
     */
//    @Bean
//    @Order(0)
//    public SecurityFilterChain bypassFilterChain(HttpSecurity http) throws Exception {
//        http.cors();
//        http.httpBasic().disable()
//                .csrf().disable();
//        http.headers().frameOptions().disable();
//        http
//                .requestMatchers((matchers) ->
//                        matchers
//                                .antMatchers(HttpMethod.POST, "/sign-in")
//                                .antMatchers(HttpMethod.POST, "/verify-email")
//                                .antMatchers(HttpMethod.POST, "/sign-up")
//                                .antMatchers(HttpMethod.POST, "/refresh")
//                                .antMatchers(HttpMethod.GET, "/category-list")
//                                .antMatchers(HttpMethod.GET, "/post-detail/**")
//                                .antMatchers(HttpMethod.GET, "/post/**")
//                                .antMatchers(HttpMethod.GET, "/feed/**")
//                )
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
//                .requestCache().disable()
//                .securityContext().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }

    /**
     * TODO
     * 아래 요청에 대해서는 Spring Security를 사용하지 않기 때문에 WARN이 나오지만,
     * 위처럼 인증 인가 필터 말고 다른 필터들을 적용해야할 이유가 있을지 궁금
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(HttpMethod.POST, "/sign-in")
                .antMatchers(HttpMethod.POST, "/verify-email")
                .antMatchers(HttpMethod.POST, "/sign-up")
                .antMatchers(HttpMethod.POST, "/refresh")
                .antMatchers(HttpMethod.GET, "/category-list")
                .antMatchers(HttpMethod.GET, "/post-detail/**")
                .antMatchers(HttpMethod.GET, "/post/**")
                .antMatchers(HttpMethod.GET, "/feed/**");
    }

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
                // 구현한 401 403 handler 등록
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //jwt 토큰 인증이므로 세션은 사용하지 않겠다. stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // request 권한 설정
                .and()
                .authorizeHttpRequests()
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
