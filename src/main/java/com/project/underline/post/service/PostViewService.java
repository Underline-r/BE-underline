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
            // redisTemplate을 안쓰고 싶었는데 repository를 쓴다면 find한뒤에 update해줘야하기때문에 -> 한번 왔다갔다해야한다는뜻
            // TODO. reditTemplate을 안쓰도록 개선
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
