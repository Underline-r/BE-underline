package com.project.underline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableRedisRepositories
public class UnderlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnderlineApplication.class, args);
    }

}
