package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchHashtagDto {
    private Long postCount;
    private String hashtagName;

    @QueryProjection
    public SearchHashtagDto(Long postCount, String hashtagName) {
        this.postCount = postCount;
        this.hashtagName = hashtagName;
    }
}
