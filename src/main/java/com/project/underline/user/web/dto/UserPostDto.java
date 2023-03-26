package com.project.underline.user.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPostDto {

    private Long postId;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedDate;

    @QueryProjection
    public UserPostDto(String content, LocalDateTime modifiedDate) {
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    @QueryProjection
    public UserPostDto(Long postId, String content, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }
}
