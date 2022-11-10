package com.project.underline.post.web.controller;


import com.project.underline.common.metadata.StatusCode;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.PostService;
import com.project.underline.post.web.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity registerPost(@RequestBody PostRequest postRequest){

        postService.registerPost(postRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(StatusCode.OK)
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/post-detail/{postId}")
    public ResponseEntity inquiryPost(@PathVariable String postId){

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(StatusCode.OK)
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @PatchMapping("/post/{postId}")
    @PutMapping("/post/{postId}")
    public ResponseEntity patchPost(@RequestBody PostRequest postRequest, @PathVariable String postId){

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(StatusCode.OK)
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable String postId){

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(StatusCode.OK)
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }
}
