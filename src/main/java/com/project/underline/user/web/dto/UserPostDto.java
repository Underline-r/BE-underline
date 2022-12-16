package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPostDto {

    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public UserPostDto(String title, String content, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }
}
