package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSourceDto {
    private Long postCount;
    private String source;

    @QueryProjection
    public SearchSourceDto(Long postCount, String source) {
        this.postCount = postCount;
        this.source = source;
    }
}
