package com.project.underline.post.service;

import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.repository.PostRedisRepository;
import com.project.underline.post.entity.repository.PostViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostViewService {
    private final PostRedisRepository postRedisRepository;
    private final PostViewRepository postViewRepository;
    private final RedisTemplate<String, PostTemp> redisTemplate;

    public PostViewService(PostRedisRepository postRedisRepository, PostViewRepository postViewRepository, @Qualifier("postTempRedisTemplate") RedisTemplate<String, PostTemp> redisTemplate) {
        this.postRedisRepository = postRedisRepository;
        this.postViewRepository = postViewRepository;
        this.redisTemplate = redisTemplate;
    }


    public Long getViewCount(Long postId) {
        try {
            Optional<PostTemp> postTemp = postRedisRepository.findById(postId);

            if (postTemp.isPresent()) {
                viewIncrease(postTemp.get());
                return postTemp.get().getPostView();
            } else {
                return postViewRepository.findByPost_PostId(postId)
                        .map(postView -> postView.getViewCount())
                        .orElseGet(() -> {
                            return setViewCount(postId).get();
                        });
            }
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
                Optional<PostTemp> postTempOptional = postRedisRepository.findById(post.getPostId());
                if (postTempOptional.isPresent()) {
                    PostTemp postTemp = postTempOptional.get();
                    postTemp.viewIncrease();
                    postRedisRepository.save(postTemp);
                } else {
                    PostTemp postTemp = new PostTemp(post.getPostId(), 1L);
                    postRedisRepository.save(postTemp);
                }
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public AtomicLong setViewCount(Long postId) {
        PostTemp postTemp = new PostTemp(postId, 1L);
        postRedisRepository.save(postTemp);
        return new AtomicLong(1L);
    }

}
