package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserReferenceDto {

    private String title;

    private String author;

    @QueryProjection
    public UserReferenceDto(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
