package com.project.underline.post.service;

import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.repository.PostRedisRepository;
import com.sun.jdi.event.ExceptionEvent;
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
    private final PostRedisRepository postRedisRepository;

    public Long getViewCount(Long postId){
        try {
            Optional<PostTemp> postTemp = postRedisRepository.findById(postId);
            viewIncrease(postTemp.get());
            return postTemp.get().getPostView();
        }catch (RuntimeException e){
            throw e;
        }
    }

    public void viewIncrease(PostTemp postTemp){
        postTemp.viewIncrease();
        postRedisRepository.save(postTemp);
    }

    public void setViewCount(Long postId){

        PostTemp postTemp = new PostTemp(postId, 0L);
        postRedisRepository.save(postTemp);
    }

}
