package com.project.underline.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostViewService {
    private final StringRedisTemplate stringRedisTemplate;

    public Long getViewCount(Long postId){

        return null;
    }

    public void setViewCount(Long postId){

        HashOperations<String, Object, Object> hashOps = stringRedisTemplate.opsForHash();

        String key = UUID.randomUUID().toString();

        Map<String, Object> values = new HashMap<>();
        values.put("postId", postId.toString()); // TODO. redis에 value값을 무조건 String으로만 저장 가능한지
        values.put("postView", "0");

        hashOps.putAll(key, values);
    }

    private String makeKey(){
        String key = "testHash" + stringRedisTemplate.randomKey();
        return key;
    }
}
