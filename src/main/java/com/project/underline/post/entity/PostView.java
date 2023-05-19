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
    @GeneratedValue
    @Column(name = "VIEW_ID")
    private Long viewId;

    @Column(name = "COUNT")
    private Long viewCount;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", unique = true)
    private Post post;

    private Long postId;

    @Builder
    public PostView(Post post) {
        this.post = post;
    }

    @Builder
    public PostView(PostTemp postTemp) {
        this.viewCount = postTemp.getPostView();
        this.postId = postTemp.getPostId(); // IN절로 한꺼번에 post조회 후 업데이트하기 위해 따로 존재하는 필드
    }

    public PostView countPostView(Post post){
        this.viewCount = viewCount +1;
        return this;
    }


}
