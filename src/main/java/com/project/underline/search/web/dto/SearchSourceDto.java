package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSourceDto {

    private Long postId;
    private String source;
    private Long userId;

    @QueryProjection
    public SearchSourceDto(Long postId, String source, Long userId) {
        this.postId = postId;
        this.source = source;
        this.userId = userId;
    }
}
