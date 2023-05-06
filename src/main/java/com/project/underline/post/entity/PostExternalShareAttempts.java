package com.project.underline.post.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "POST_EXTERNAL_SHARE_ATTEMPTS")
public class PostExternalShareAttempts {
    @Id
    @GeneratedValue
    @Column(name="PESA_ID")
    private Long shareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name="SHARE_TARGET")
    private String shareTarget;

    @Column(name="ATTEMPT_COUNT")
    private Long attemptCount;

    public PostExternalShareAttempts(Long postId, String shareTarget, Long attemptCount){
        this.post = new Post(postId);
        this.shareTarget = shareTarget;
        this.attemptCount = attemptCount;
    }

    public PostExternalShareAttempts update(){
        this.attemptCount += 1;
        return this;
    }

}
