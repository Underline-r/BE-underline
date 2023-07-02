package com.project.underline.search.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchHashtagDto {
    private Long postCount;
    private String hashtagName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime recentUpdatedAt;

    @QueryProjection
    public SearchHashtagDto(Long postCount, String hashtagName) {
        this.postCount = postCount;
        this.hashtagName = hashtagName;
    }
}
