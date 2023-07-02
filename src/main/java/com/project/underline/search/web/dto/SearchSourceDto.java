package com.project.underline.search.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchSourceDto {
    private Long postCount;
    private String source;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime recentUpdatedAt;

    @QueryProjection
    public SearchSourceDto(Long postCount, String source) {
        this.postCount = postCount;
        this.source = source;
    }
}
