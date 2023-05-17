package com.project.underline.post.service;

import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.post.entity.PostTemp;
import com.project.underline.post.entity.PostView;
import com.project.underline.post.entity.repository.PostRedisRepository;
import com.project.underline.post.entity.repository.PostViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostViewService {
    private final PostRedisRepository postRedisRepository;
    private final PostViewRepository postViewRepository;
    private final RedisTemplate<String, PostTemp> redisTemplate;

    public PostViewService(PostRedisRepository postRedisRepository, PostViewRepository postViewRepository, @Qualifier("redisTemplateConfig") RedisTemplate<String, PostTemp> redisTemplate) {
        this.postRedisRepository = postRedisRepository;
        this.postViewRepository = postViewRepository;
        this.redisTemplate = redisTemplate;
    }


    public Long getViewCount(Long postId) {
        try {
            Optional<PostTemp> postTemp = postRedisRepository.findById(postId);

            if(postTemp.isPresent()){
                viewIncrease(postTemp.get());
                return postTemp.get().getPostView();
            }else {
                return postViewRepository.findByPost_PostId(postId)
                        .map(PostView::getViewCount)
                        .orElseGet(() -> {
                            return setViewCount(postId);
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


    public long setViewCount(Long postId) {
        PostTemp postTemp = new PostTemp(postId, 0L);
        postRedisRepository.save(postTemp);
        return 1L;
    }

}
