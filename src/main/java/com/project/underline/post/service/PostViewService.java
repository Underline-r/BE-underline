package com.project.underline.post.service;

import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.repository.PostRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostViewService {
    private final PostRedisRepository postRedisRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public PostViewService(PostRedisRepository postRedisRepository, RedisTemplate<String, Object> redisTemplate) {
        this.postRedisRepository = postRedisRepository;
        this.redisTemplate = redisTemplate;
    }

    public Long getViewCount(Long postId) {
        try {
            Optional<PostTemp> postTemp = postRedisRepository.findById(postId);
            viewIncrease(postTemp.get());
            return postTemp.get().getPostView();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void viewIncrease(PostTemp postTemp) {
        postTemp.viewIncrease();
        postRedisRepository.save(postTemp);
    }

    public void postListViewIncrease(List<FeedPost> postList) {
        try {
            for (FeedPost post : postList) {
                String key = "PostTemp:" + post.getPostId();
                redisTemplate.opsForHash().increment(key, "postView", 1L);
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public void setViewCount(Long postId) {
        PostTemp postTemp = new PostTemp(postId, 0L);
        postRedisRepository.save(postTemp);
    }

}
