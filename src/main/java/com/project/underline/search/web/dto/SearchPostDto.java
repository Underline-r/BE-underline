package com.project.underline.search.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchPostDto {
    private Long postId;
    private String content;
    private String source;
    private String userNickname;
    private String userProfileImage;
    private Boolean isPicked;
    private Boolean isBookmarked;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime recentUpdatedAt;

    @QueryProjection
    public SearchPostDto(Long postId, String content, String source, String userNickname, String userProfileImage, Boolean isPicked, Boolean isBookmarked, LocalDateTime recentUpdatedAt) {
        this.postId = postId;
        this.content = content;
        this.source = source;
        this.userNickname = userNickname;
        this.userProfileImage = userProfileImage;
        this.isPicked = isPicked;
        this.isBookmarked = isBookmarked;
        this.recentUpdatedAt = recentUpdatedAt;
    }
}
