package com.project.underline.post.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "Post")
public class Post extends BaseTimeEntity {

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Builder
    public Post(Long userId,String title,ContentType contentType,String content){
        this.userId = userId;
        this.title = title;
        this.contentType = contentType;
        this.content = content;
    }
}

