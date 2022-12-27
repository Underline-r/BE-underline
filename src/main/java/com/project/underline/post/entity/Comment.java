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
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long commentId;

    @Column(name="COTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Comment(Post post, User user, String content){
        this.post = post;
        this.user = user;
        this.content = content;
    }
}