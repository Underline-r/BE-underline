package com.project.underline.bookmark.entity;

import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.post.entity.Post;
import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "BOOKMARK")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "bookmarkId")
    private Long bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    public Bookmark(User user,Post post){
        this.user = user;
        this.post = post;
    }
}
