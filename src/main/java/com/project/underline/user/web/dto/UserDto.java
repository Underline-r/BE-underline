package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserDto {
    private String email;
    private String nickname;

    @QueryProjection
    public UserDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
