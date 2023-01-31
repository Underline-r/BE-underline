package com.project.underline.post.entity;

import com.project.underline.category.entity.PostCategoryRelation;
import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.reference.entity.Reference;
import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_ID")
    private Reference reference;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    @Lob
    private String content;


    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<PostCategoryRelation> categoryList = new ArrayList<PostCategoryRelation>();

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<Hashtag>();

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();


    @Builder
    public Post(User user,String title,String content){
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Post(Long id){
        this.postId = id;
    }

    public Post update(String title,String content){
        this.title = title;
        this.content = content;
        return this;
    }

    public Post setHashtagsAndCategory(List<Hashtag> hashtags, List<PostCategoryRelation> postCategoryRelations){
        this.hashtags = hashtags;
        this.categoryList = postCategoryRelations;
        return this;
    }

    public ContentType contentSize(){
        // 기능 삭제
        if(content.length() > 365){
            return ContentType.LONG;
        }
        return ContentType.SHORT;
    }
}

