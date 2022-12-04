package com.project.underline.post.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.PostService;
import com.project.underline.post.web.dto.PostDetailResponse;
import com.project.underline.post.web.dto.PostRequest;
import com.project.underline.post.web.dto.UserCreatedPostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity registerPost(@RequestBody PostRequest postRequest){

        postService.registerPost(postRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/post-detail/{postId}")
    public ResponseEntity inquiryPost(@PathVariable Long postId){

        PostDetailResponse postDetailResponse = postService.inquiryPost(postId);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(postDetailResponse)
                        .build()
                , HttpStatus.OK);
    }

    @PatchMapping("/post/{postId}")
    @PutMapping("/post/{postId}")
    public ResponseEntity patchPost(@PathVariable Long postId, @RequestBody PostRequest postRequest){

        postService.patchPost(postId,postRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)

                        .build()
                , HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){

        postService.deletePost(postId);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity inquiryUserCreatedPost(@RequestParam("userNickname") String userNickname){
        UserCreatedPostListResponse userCreatedPostList = postService.inquiryUserCreatedPost(userNickname);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(userCreatedPostList)
                        .build()
                , HttpStatus.OK);
    }


}
