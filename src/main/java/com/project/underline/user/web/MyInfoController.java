package com.project.underline.user.web;

import com.project.underline.category.web.dto.UserCategoryListRequest;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.util.S3Service;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.web.dto.CommentResponse;
import com.project.underline.user.service.MyInfoService;
import com.project.underline.user.service.UserProfileService;
import com.project.underline.user.web.dto.*;
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
    private final UserProfileService profileService;

    @GetMapping()
    public ResponseEntity<UserProfileDto> getMyProfile() {
        Long id = SecurityUtil.getCurrentUserId();
        UserProfileDto userProfile = profileService.getUserProfile(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, userProfile), HttpStatus.OK
        );
    }

    @GetMapping("/following")
    public ResponseEntity<FollowUserInfoDto> getFollowingList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<FollowUserInfoDto> followingList = profileService.getFollowingList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, followingList), HttpStatus.OK
        );
    }

    @GetMapping("/followers")
    public ResponseEntity<FollowUserInfoDto> getFollowerList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<FollowUserInfoDto> followerList = profileService.getFollowerList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, followerList), HttpStatus.OK
        );
    }

    @GetMapping("/posts")
    public ResponseEntity<UserPostDto> getUserPostList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<UserPostDto> postList = profileService.getUserPostList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, postList), HttpStatus.OK
        );
    }

    @GetMapping("/hashtags")
    public ResponseEntity<String> getUserHashtagList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<String> hashtagList = profileService.getUserHashtagList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, hashtagList), HttpStatus.OK
        );
    }

    @GetMapping("/categories")
    public ResponseEntity<String> getUserCategoryList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<String> categoryList = profileService.getUserCategoryList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, categoryList), HttpStatus.OK
        );
    }

    @GetMapping("/sources")
    public ResponseEntity<String> getUserSourceList() {
        Long id = SecurityUtil.getCurrentUserId();
        List<UserSourceDto> sourceList = profileService.getUserSourceList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, sourceList), HttpStatus.OK
        );
    }

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
        Long userId = SecurityUtil.getCurrentUserId();
        String path = "profile/" + userId;
        s3Service.upload(file, path);

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
