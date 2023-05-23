package com.project.underline.post.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.concurrent.atomic.AtomicLong;

@RedisHash("PostTemp")
@ToString
@Getter
public class PostTemp {
    // TODO. Redis에 사용할 Hash용 객체, 더 좋은 네이밍 추천 바람

    private static final long serialVersionUID = 1370692830319429806L;

    @Id
    private Long postId;

    private Long postView;  // Long -> AtomicLong, 동시성 문제 고려

    public PostTemp(Long postId, Long postView) {
        this.postId = postId;
        this.postView = postView;
    }

    public PostTemp viewIncrease() {
        AtomicLong viewCount = getPostViewAtomic();
        viewCount.incrementAndGet();
        this.setPostView(viewCount.get());
        return this;
    }

    public void setPostView(Long postView) {
        this.postView = postView;
    }

    public AtomicLong getPostViewAtomic() {
        return new AtomicLong(this.postView);
    }
}
