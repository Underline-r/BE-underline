package com.project.underline.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
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
    private String contentType;

    @Column(name="HASHTAG_ID")
    private Long hashTagId;

    @Column(name="CONTENT")
    private String content;


}
