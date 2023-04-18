package com.project.underline.post.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.underline.category.entity.PostCategoryRelation;
import com.project.underline.post.entity.Comment;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.reference.entity.Reference;
import com.project.underline.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostSearchDto {

    private Long postId;
    private User user;
    private Reference reference;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private List<PostCategoryRelation> categoryList = new ArrayList<PostCategoryRelation>();
    private List<Hashtag> hashtags = new ArrayList<Hashtag>();
    private List<Comment> comments = new ArrayList<Comment>();

    @QueryProjection
    public PostSearchDto(Long postId, String content, LocalDateTime createdDate) {
        this.postId = postId;
        this.content = content;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public PostSearchDto(Long postId, User user, Reference reference, String content, List<PostCategoryRelation> categoryList, List<Hashtag> hashtags, List<Comment> comments) {
        this.postId = postId;
        this.user = user;
        this.reference = reference;
        this.content = content;
        this.categoryList = categoryList;
        this.hashtags = hashtags;
        this.comments = comments;
    }
}
