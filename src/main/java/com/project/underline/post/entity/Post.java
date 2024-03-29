package com.project.underline.post.entity;

import com.project.underline.bookmark.entity.Bookmark;
import com.project.underline.category.entity.PostCategoryRelation;
import com.project.underline.common.util.BaseTimeEntity;
import com.project.underline.source.entity.Source;
import com.project.underline.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID")
    private Source source;

    @Column(name="CONTENT")
    @NotNull
    private String content;


    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    @NotNull
    private List<PostCategoryRelation> categoryList = new ArrayList<PostCategoryRelation>();

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<Hashtag>();

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Pick> picks = new ArrayList<Pick>();


    @Builder
    public Post(User user, String content, Source source){
        this.user = user;
        this.content = content;
        this.source = source;

        if(!source.getPostList().contains(this)){
            source.getPostList().add(this);
        }
    }
    @Builder
    public Post(User user,String content){
        this.user = user;
        this.content = content;
    }

    public Post(Long id){
        this.postId = id;
    }

    public Post update(String content){
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

    public void removeAllHashtagsAndCategory() {
        for (Hashtag hashtag : this.hashtags) {
            hashtag.setPost(null);
        }

        this.hashtags.clear();

        for (PostCategoryRelation category : this.categoryList) {
            category.setPost(null);
        }

        this.categoryList.clear();


    }
}

