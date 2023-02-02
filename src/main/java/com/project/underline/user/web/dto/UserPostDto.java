package com.project.underline.user.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPostDto {

    private String content;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public UserPostDto(String content, LocalDateTime modifiedDate) {
        this.content = content;
        this.modifiedDate = modifiedDate;
    }
}
