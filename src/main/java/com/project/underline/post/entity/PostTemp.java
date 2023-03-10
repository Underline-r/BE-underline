package com.project.underline.post.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("PostTemp")
@Getter
@ToString
public class PostTemp {
    // TODO. Redis에 사용할 Hash용 객체, 더 좋은 네이밍 추천 바람

    private static final long serialVersionUID = 1370692830319429806L;

    @Id
    private Long postId;

    private Long postView;

    public PostTemp(Long postId, Long postView) {
        this.postId = postId;
        this.postView = postView;
    }

    public PostTemp viewIncrease(){
        this.postView += 1;
        return this;
    }
}
