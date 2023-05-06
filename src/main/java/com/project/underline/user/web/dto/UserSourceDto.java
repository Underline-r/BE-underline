package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserSourceDto {

    private String title;


    @QueryProjection
    public UserSourceDto(String title) {
        this.title = title;
    }
}
