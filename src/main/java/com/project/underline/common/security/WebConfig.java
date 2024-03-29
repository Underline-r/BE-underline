package com.project.underline.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
/**
 * cors config
 */
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//cors를 적용할 URL패턴 정의
                .allowedOriginPatterns("*")//자원 공유 허락할 Origin 허락
                .allowedMethods("*")//허락할 HTTP method 지정
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}