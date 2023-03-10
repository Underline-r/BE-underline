package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Post;
import lombok.Getter;

@Getter
public class PostDetailResponse {
    private String content;
    private Long userId;
    private Long viewCount;

    public PostDetailResponse(Post post){
        this.content = post.getContent();
        this.userId = post.getUser().getId();
    }

    public PostDetailResponse(Post post, Long viewCount){
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.viewCount = viewCount;
    }
}
