package com.project.underline.user.entity;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.common.util.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "USERS")
public class User extends BaseTimeEntity {
    /* table 명 user -> users로 변경해둠 */

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String description;
    private String imagePath;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "fromUser")
    private List<UserFollowRelation> fromUserFollowRelations = new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    private List<UserFollowRelation> toUserFollowRelations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserCategoryRelation> userCategoryRelations = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.authority = Authority.USER;
    }

    public User(Long id){
        this.id = id;
    }

    public void changeProfile(String nickname, String description, String imagePath) {
        this.nickname = nickname;
        this.description = description;
        this.imagePath = imagePath;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
