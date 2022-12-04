package com.project.underline.user.web.dto;

import com.project.underline.user.entity.User;
import lombok.Data;

@Data
public class UserProfileDto {

    // TODO : 프로필 정보 정해지면 게시글 수 조회도 추가

    private String email;
    private String nickname;
    private boolean subscribeState;
    private int followerCount;
    private int followingCount;


    public UserProfileDto EntityToDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        return this;
    }
}
