package com.project.underline.post.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.CommentService;
import com.project.underline.post.web.dto.CommentRequest;
import com.project.underline.post.web.dto.CommentResponse;
import com.project.underline.post.web.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity inquiryComments(@PathVariable Long postId){

        CommentResponse commentResponse = commentService.inquiryComments(postId);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(commentResponse)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity registerComments(@PathVariable Long postId, @RequestBody CommentRequest commentRequest){

        commentService.registerComments(postId,commentRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @PatchMapping("/{postId}/comments")
    public ResponseEntity patchPost(@PathVariable Long postId, @RequestBody CommentRequest commentRequest){

        commentService.patchComment(postId,commentRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)

                        .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments")
    public ResponseEntity deleteComments(@PathVariable Long postId, @RequestBody CommentRequest commentRequest){

        commentService.deleteComments(postId,commentRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }
}
