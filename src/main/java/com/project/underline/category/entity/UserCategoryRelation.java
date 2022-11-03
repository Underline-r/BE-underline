package com.project.underline.category.entity;

import com.project.underline.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Getter
@Table(name = "UserCategoryRelation")
public class UserCategoryRelation {

    @Id
    @GeneratedValue
    @Column(name="UCS_ID")
    private Long userCategoryRelationId;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="CATEGORY")
    private String category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserCategoryRelation(Long userId, String eachCategory) {
    }
}
