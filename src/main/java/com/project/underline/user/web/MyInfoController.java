package com.project.underline.user.web;

import com.project.underline.category.web.dto.UserCategoryListRequest;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.util.S3Service;
import com.project.underline.post.web.dto.CommentResponse;
import com.project.underline.user.service.MyInfoService;
import com.project.underline.user.web.dto.SignupRequestDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-info")
public class MyInfoController {

    private final MyInfoService infoService;
    private final S3Service s3Service;

    @PatchMapping()
    public ResponseEntity changeUserProfile(@RequestBody UserProfileDto profileDto) {
        infoService.changeUserProfile(profileDto);

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PatchMapping("/category")
    public ResponseEntity changeUserCategory(@RequestBody UserCategoryListRequest category) {
        infoService.changeUserCategory(category.getCodes());

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PatchMapping("/password")
    public ResponseEntity changeUserPassword(@RequestBody SignupRequestDto signupRequestDto) {
        infoService.changeUserPassword(signupRequestDto.getPassword());

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PostMapping("/image")
    public ResponseEntity regUserProfileImage(@RequestPart MultipartFile file) throws IOException {
        s3Service.upload(file, "profile");

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @GetMapping("/comments")
    public ResponseEntity<CommentResponse> listComments() {
        CommentResponse commentList = infoService.listComments();
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, commentList), HttpStatus.OK
        );
    }

    @GetMapping("/pick-posts")
    public ResponseEntity<UserPostDto> listPickPost() {
        List<UserPostDto> pickPostList = infoService.listPickPost();
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, pickPostList), HttpStatus.OK
        );
    }
}
