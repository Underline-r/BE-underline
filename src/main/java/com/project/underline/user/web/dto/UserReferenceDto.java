package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserReferenceDto {

    private String title;


    @QueryProjection
    public UserReferenceDto(String title) {
        this.title = title;
    }
}
