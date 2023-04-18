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
    private boolean isMyPage;
    private String description;
    private String imagePath;

    public UserProfileDto() {
    }

    // Lombok 사용중 변수명 is가 자동으로 사라지는 이슈를 getter 직접 구현으로 해결
    public boolean getIsMyPage() {
        return isMyPage;
    }

    public UserProfileDto EntityToDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        return this;
    }

    @QueryProjection
    public UserProfileDto(String email, String nickname, String description, String imagePath) {
        this.email = email;
        this.nickname = nickname;
        this.description = description;
        this.imagePath = imagePath;
    }

    @QueryProjection
    public UserProfileDto(String email, String nickname, String description, String image, boolean subscribeState, int followerCount, int followingCount) {
        this.email = email;
        this.nickname = nickname;
        this.description = description;
        this.subscribeState = subscribeState;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.imagePath = image;
    }
}
