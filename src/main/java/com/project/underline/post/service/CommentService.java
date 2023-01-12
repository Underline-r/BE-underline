package com.project.underline.post.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Comment;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.CommentRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.CommentRequest;
import com.project.underline.post.web.dto.CommentResponse;
import com.project.underline.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public void registerComments(Long postId, CommentRequest commentRequest) {
        commentRepository.save(new Comment(checkPostExistence(postId)
                ,userService.existUser(SecurityUtil.getCurrentUserId())
                ,commentRequest.getComment()));
    }

    public CommentResponse inquiryComments(Long postId) {
        return new CommentResponse(commentRepository.findAllByPost(checkPostExistence(postId)));
    }

    public void deleteComments(Long postId, CommentRequest commentRequest) {
        commentRepository.delete(checkCommentOwner(postId,commentRequest.getCommentId()));
    }

    private Post checkPostExistence(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_POST));
    }

    private Comment checkCommentOwner(Long postId,Long commentId){
        checkPostExistence(postId);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new UnderlineException(ErrorCode.WRONG_APPROACH));
        if(comment.getUser().getId().equals(SecurityUtil.getCurrentUserId())){
            return comment;
        }else{
            throw new UnderlineException(ErrorCode.WRONG_APPROACH);
        }
    }
}
