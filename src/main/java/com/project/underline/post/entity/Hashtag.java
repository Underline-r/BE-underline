package com.project.underline.post.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "HASHTAG")
public class Hashtag {

    @Id
    @GeneratedValue
    @Column(name="HASHTAG_ID")
    private Long hashtagId;

    @Column(name="HASHTAG_NAME")
    private String hashtagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Builder
    public Hashtag(Post post,String hashtagName){
        this.post = post;
        this.hashtagName = hashtagName;
    }
}
