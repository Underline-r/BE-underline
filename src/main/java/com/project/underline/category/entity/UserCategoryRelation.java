package com.project.underline.category.entity;

import com.project.underline.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
                name="CATEGORY_RELATION_UK",
                columnNames = {"USER_ID", "CATEGORY"}
        )
}
)
public class UserCategoryRelation {

    @Id
    @GeneratedValue
    @Column(name="UCS_ID")
    private Long userCategoryRelationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name="CATEGORY")
    private String category;


    /**
     * 연관관계 편의 메서드
     */
    public void addUser(User user) {
        this.user = user;
        if(!user.getUserCategoryRelations().contains(this)){
            user.getUserCategoryRelations().add(this);
        }
    }

    public UserCategoryRelation(String category) {
        this.category = category;
    }
}
