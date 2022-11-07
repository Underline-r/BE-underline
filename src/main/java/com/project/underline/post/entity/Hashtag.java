package com.project.underline.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "HashTag")
public class Hashtag {

    @Id
    @GeneratedValue
    @Column(name="HASHTAG_ID")
    private Long hashtagId;

    @Column(name="POST_ID")
    private Long postId;

    @Column(name="HASHTAG_NAME")
    private String hashtagName;
}
