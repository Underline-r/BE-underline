package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchHashtagDto {
    private Long postId;
    private Long postCount;

    @QueryProjection
    public SearchHashtagDto(Long postId, Long postCount) {
        this.postId = postId;
        this.postCount = postCount;
    }
}
