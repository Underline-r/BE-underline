package com.project.underline.post.service;

import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.repository.PostRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostViewService {
    private final RedisTemplate redisTemplate;
    private final PostRedisRepository postRedisRepository;

    public Long getViewCount(Long postId){
        Optional<PostTemp> postTemp = postRedisRepository.findById(postId);
        return postTemp.get().getPostView();
    }

    public void setViewCount(Long postId){

        PostTemp postTemp = new PostTemp(postId, 0L);
        postRedisRepository.save(postTemp);
    }

    private String makeKey(){
        String key = "testHash" + redisTemplate.randomKey();
        return key;
    }
}
