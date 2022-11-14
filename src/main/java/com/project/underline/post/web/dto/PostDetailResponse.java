package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Post;
import lombok.Getter;

@Getter
public class PostDetailResponse {
    private String title;
    private String content;
    private Long userId;

    public PostDetailResponse(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
    }
}
