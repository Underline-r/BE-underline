package com.project.underline.post.entity;

import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "Pick")
public class Pick {

    @Id
    @GeneratedValue
    @Column(name="PICK_ID")
    private Long pickId;

    @Column(name="POST_ID")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Pick(Long pickId,Long postId,User user){
        this.pickId = pickId;
        this.postId = postId;
        this.user = user;
    }

    public Pick update(Long pickId,Long postId,User user){
        this.pickId = pickId;
        this.postId = postId;
        this.user = user;
        return this;
    }
}
