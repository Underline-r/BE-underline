package com.project.underline.category.entity;


import com.project.underline.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "CATEGORY_RELATION_UK",
                        columnNames = {"PCR_ID", "CATEGORY_CODE"}
                )
        }
)
public class PostCategoryRelation {

    @Id
    @GeneratedValue
    @Column(name = "PCR_ID")
    private Long postCategoryRelationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "CATEGORY_CODE")
    private String categoryCode;

    @Builder
    public PostCategoryRelation(Post post, String categoryCode){
        this.post = post;
        this.categoryCode = categoryCode;
    }
}
