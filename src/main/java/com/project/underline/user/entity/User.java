package com.project.underline.user.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "users")
public class User extends BaseTimeEntity {
    /* table 명 user -> users로 변경해둠 */

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @ManyToOne
    @JoinColumn
    private final User userFollowing = this;

    @ManyToOne
    @JoinColumn
    private final User userFollower = this;

    @OneToMany(mappedBy = "userFollowing")
    private final List<User> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "userFollower")
    private final List<User> followerList = new ArrayList<>();

    public void addFollowing(User following) {
        this.followingList.add(following);

        if(!following.getFollowerList().contains(this)) {
            following.getFollowerList().add(this);
        }
        //연관관계의 주인을 통한 확인
        if(!following.getUserFollower().getFollowerList().contains(this)) {
            following.getUserFollower().getFollowerList().add(this);
        }
    }

    public void unFollowing(User following) {
        this.followingList.remove(following);
        following.getFollowerList().remove(this);
        following.getUserFollower().getFollowerList().remove(this);
    }

    @Builder
    public User(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.authority = Authority.USER;
    }
}
