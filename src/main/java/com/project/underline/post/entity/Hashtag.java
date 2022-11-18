package com.project.underline.post.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "Hashtag")
public class Hashtag {

    @Id
    @GeneratedValue
    @Column(name="HASHTAG_ID")
    private Long hashtagId;

    @Column(name="POST_ID")
    private Long postId;

    @Column(name="HASHTAG_NAME")
    private String hashtagName;

    @Builder
    public Hashtag(Long postId,String hashtagName){
        this.postId = postId;
        this.hashtagName = hashtagName;
    }
}
