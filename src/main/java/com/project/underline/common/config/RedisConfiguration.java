package com.project.underline.common.config;

import com.project.underline.common.util.FeedPostSerializer;
import com.project.underline.post.entity.PostTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        config.setPassword(RedisPassword.of(password)); // Redis 암호 설정
        return new JedisConnectionFactory(config);
    }

    @Bean("postTempRedisTemplate")
    public RedisTemplate<String, PostTemp> redisTemplateConfig(RedisConnectionFactory redisConnectionFactory) {
        // TODO. RedisTemplate 제너릭하게 사용할 수 있도록 GenericRedisTemplate 생성 필요
        RedisTemplate<String, PostTemp> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(PostTemp.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(PostTemp.class));
        return template;
    }

    @Bean
    public FeedPostSerializer feedPostSerializer() {
        return new FeedPostSerializer();
    }

}

