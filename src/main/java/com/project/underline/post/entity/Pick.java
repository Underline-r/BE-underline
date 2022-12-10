package com.project.underline.post.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "PICK")
public class Pick {

    @Id
    @GeneratedValue
    @Column(name="PICK_ID")
    private Long pickId;

    @Column(name="POST_ID")
    private Long postId;

    @Column(name="USER_ID")
    private Long userId;

    @Builder
    public Pick(Long pickId,Long postId,Long userId){
        this.pickId = pickId;
        this.postId = postId;
        this.userId = userId;
    }

    public Pick update(Long pickId,Long postId,Long userId){
        this.pickId = pickId;
        this.postId = postId;
        this.userId = userId;
        return this;
    }
}
