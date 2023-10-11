package com.project.underline.post.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.underline.category.entity.PostCategoryRelation;
import com.project.underline.common.util.SecurityUtil;
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
    private Long viewCount;
    private String userNickname;
    private String userProfileImage;

    private Long pickCount;
    private Long commentCount;

    private Boolean isPicked;
    private Boolean isBookmarked;
    private Boolean isFollowed;

    private List<String> hashtags;
    private String oldestComment;

    private List<String> categoryList;
    private Boolean isMyPost = false;

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
        this.content = post.getContent();
        this.userId = post.getUser().getId();

        /* 신규추가 항목 */
        this.postId = post.getPostId();
        this.createdDate = post.getCreatedDate();
        this.userNickname = post.getUser().getNickname();
        this.userProfileImage = post.getUser().getImagePath();
    }

    public PostDetailResponse(Post post, Long viewCount){
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.viewCount = viewCount;
        this.userNickname = post.getUser().getNickname();

        /* 신규추가 항목 */
        this.postId = post.getPostId();
        this.createdDate = post.getCreatedDate();

        if(post.getSource() != null){
            this.source = post.getSource().getTitle();
        }

        if(post.getHashtags() != null){
            this.hashtags = post.getHashtags().stream()
                    .map(h -> h.getHashtagName())
                    .collect(Collectors.toList());
        }

        if(post.getUser().getImagePath() != null){
            this.userProfileImage = post.getUser().getImagePath();
        }

        if (post.getCategoryList() != null) {
            this.categoryList = post.getCategoryList().stream()
                    .map(category -> category.getCategoryCode())
                    .collect(Collectors.toList());
        }

        checkIsMyPage();
    }

    private void checkIsMyPage() {
        if (userId != null) {
            if (SecurityUtil.getCurrentUserId().equals(userId)) {
                this.isMyPost = true;
            }
        }
    }
}
