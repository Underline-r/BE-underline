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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Builder
    public PostView(Post post) {
        this.post = post;
    }

    public PostView countPostView(Post post){
        this.viewCount = viewCount +1;
        return this;
    }


}
