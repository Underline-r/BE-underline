package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Comment;
import lombok.Getter;

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
        private Long userId;
        private String comment;

        public EachComment(Comment comment){
            this.userId = comment.getUser().getId();
            this.comment = comment.getContent();
        }
    }
}
