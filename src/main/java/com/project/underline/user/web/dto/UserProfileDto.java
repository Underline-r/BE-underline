package com.project.underline.user.web.dto;

import com.project.underline.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserProfileDto {

    // TODO : 프로필 정보 정해지면 게시글 수 조회도 추가

    private String email;
    private String nickname;
    private boolean subscribeState;
    private int followerCount;
    private int followingCount;
    private boolean myPage;
    private String description;

    public UserProfileDto() {
    }


    public UserProfileDto EntityToDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        return this;
    }

    @QueryProjection
    public UserProfileDto(String email, String nickname, String description, boolean subscribeState, int followerCount, int followingCount) {
        this.email = email;
        this.nickname = nickname;
        this.description = description;
        this.subscribeState = subscribeState;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }
}
