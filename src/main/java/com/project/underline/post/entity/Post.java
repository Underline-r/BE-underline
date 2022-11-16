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


    @Builder
    public Post(Long userId,String title,String content){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.contentType = contentSize();
    }

    public Post update(String title,String content){
        // TO-DO. 컨텐츠 타입을 365자를 기준으로하는데 수정시 컨텐츠 타입이 변하는 경우는 어떻게 처리? -> 컨텐츠 타입이 바뀌어도 되는건가요?
        this.title = title;
        this.content = content;
        this.contentType = contentSize();
        return this;
    }

    public ContentType contentSize(){
        if(content.length() > 365){
            return ContentType.LONG;
        }
        return ContentType.SHORT;
    }
}

