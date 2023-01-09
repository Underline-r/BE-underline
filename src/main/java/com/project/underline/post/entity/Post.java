package com.project.underline.post.entity;

import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "POST")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="POST_ID")
    private Long postId;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT_TYPE")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name="CONTENT")
    @Lob
    private String content;

    @Column(name="CATEGORY_CODE")
    private String categoryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<Hashtag>();

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();


    @Builder
    public Post(User user,String title,String content,String categoryCode){
        this.user = user;
        this.title = title;
        this.categoryCode = categoryCode;
        this.content = content;
        this.contentType = contentSize();
    }

    public Post update(String title,String content,String categoryCode){
        // TO-DO. 컨텐츠 타입을 365자를 기준으로하는데 수정시 컨텐츠 타입이 변하는 경우는 어떻게 처리? -> 컨텐츠 타입이 바뀌어도 되는건가요?
        this.title = title;
        this.categoryCode = categoryCode;
        this.content = content;
        this.contentType = contentSize();
        return this;
    }

    public Post(Long id){
        this.postId = id;
    }

    public ContentType contentSize(){
        if(content.length() > 365){
            return ContentType.LONG;
        }
        return ContentType.SHORT;
    }
}

