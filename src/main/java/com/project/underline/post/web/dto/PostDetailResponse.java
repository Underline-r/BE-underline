package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Post;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResponse {
    private Long postId;
    private String content;
    private String reference;
    private LocalDateTime createdDate;

    private Long userId;
    private String userNickname;
    private String userProfileImage;

    private Long viewCount;
    private Long likeCount;
    private Long commentCount;

    private Long isPicked;
    private Long isBookmarked;
    private Long isFollwed;

    private List<String> hashtags;
    private String oldestComment;

    public PostDetailResponse(Post post){
        this.content = post.getContent();
        this.userId = post.getUser().getId();
    }
}
