package com.project.underline.post.entity;

import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "PICK")
public class Pick extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="PICK_ID")
    private Long pickId;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Pick(Long pickId,Post post,User user){
        this.pickId = pickId;
        this.post = post;
        this.user = user;
    }

    public Pick update(Long pickId,Post post,User user){
        this.pickId = pickId;
        this.post = post;
        this.user = user;
        return this;
    }
}
