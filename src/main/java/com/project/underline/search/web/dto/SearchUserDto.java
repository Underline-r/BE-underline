package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDto {
    private Long userId;
    private String userProfileImage;
    private String userNickname;
//    private Boolean isFollowed;

//    public boolean getIsFollowed() {
//        return isFollowed;
//    }

    @QueryProjection
    public SearchUserDto(Long userId, String userProfileImage, String userNickname) {
        this.userId = userId;
        this.userProfileImage = userProfileImage;
        this.userNickname = userNickname;
    }
}
