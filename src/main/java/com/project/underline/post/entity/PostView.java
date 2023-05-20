package com.project.underline.post.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "POST_VIEW")
public class PostView extends BaseTimeEntity {

    @Id
    @Column(name = "POST_ID")
    private Long postId;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
//    private Post post;

    @Column(name = "COUNT")
    private Long viewCount;

    @Builder
    public PostView(Post post,PostTemp postTemp) {
        this.postId = post.getPostId();
        this.viewCount = postTemp.getPostView();
    }
    public PostView countPostView(Post post){
        this.viewCount = viewCount +1;
        return this;
    }
}
