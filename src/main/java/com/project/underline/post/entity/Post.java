package com.project.underline.post.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name="POST_ID")
    private Long postId;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT_TYPE")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name="CONTENT")
    @Lob
    private String content;

    @Builder
    public Post(Long userId,String title,ContentType contentType,String content){
        this.userId = userId;
        this.title = title;
        this.contentType = contentType;
        this.content = content;
    }
}

