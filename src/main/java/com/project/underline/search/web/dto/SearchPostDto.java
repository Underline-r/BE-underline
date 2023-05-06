package com.project.underline.search.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPostDto {
    private Long postId;
    private String content;
    private String source;
    private String userNickname;

    @QueryProjection
    public SearchPostDto(Long postId, String content, String source, String userNickname) {
        this.postId = postId;
        this.content = content;
        this.source = source;
        this.userNickname = userNickname;
    }
}
