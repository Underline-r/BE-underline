package com.project.underline.post.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDetailResponse {
    private Long postId;
    private String content;
    private String source;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    private Long userId;
    private String userNickname;
    private String userProfileImage;

    private Long viewCount;
    private Long pickCount;
    private Long commentCount;

    private Boolean isPicked;
    private Boolean isBookmarked;
    private Boolean isFollowed;

    private List<String> hashtags;
    private String oldestComment;

    public PostDetailResponse setUserCheck(Boolean isPicked,Boolean isBookmarked,Boolean isFollowed){
        this.isPicked = isPicked;
        this.isBookmarked = isBookmarked;
        this.isFollowed = isFollowed;
        return this;
    }

    public PostDetailResponse setCountOption(Long pickCount, Long commentCount, String oldestComment){
        this.viewCount = 0L; // TODO. 아직 조회수 기능 구현 안되어서 0으로 제공, 추후 로직 수정
        this.pickCount = pickCount;
        this.commentCount = commentCount;
        this.oldestComment = oldestComment;
        return this;
    }
    public PostDetailResponse(Post post){
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.source = post.getReference().getTitle();
        this.createdDate = post.getCreatedDate();

        this.userId = post.getUser().getId();
        this.userNickname = post.getUser().getNickname();
        this.userProfileImage = post.getUser().getImagePath();

        this.hashtags = post.getHashtags().stream()
                .map(Hashtag::getHashtagName)
                .collect(Collectors.toList());
    }
}
