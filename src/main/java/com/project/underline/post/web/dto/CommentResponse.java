package com.project.underline.post.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.underline.post.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponse {
    private List<EachComment> comments;

    public CommentResponse(List<Comment> comments){
        this.comments = new ArrayList<EachComment>();

        for (Comment eachComment: comments) {
            this.comments.add(new EachComment(eachComment));
        }
    }

    @Getter
    public static class EachComment{
        private Long commentId;
        private Long userId;
        private String nickename;
        private String imagePath;
        private String comment;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime modifiedDate;

        public EachComment(Comment comment){
            this.commentId = comment.getCommentId();
            this.userId = comment.getUser().getId();
            this.nickename = comment.getUser().getNickname();
            this.imagePath = comment.getUser().getImagePath();
            this.comment = comment.getContent();
            this.modifiedDate = comment.getModifiedDate();
        }
    }
}
