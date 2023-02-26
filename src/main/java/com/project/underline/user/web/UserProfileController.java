package com.project.underline.user.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.user.service.UserProfileService;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.project.underline.user.web.dto.UserProfileDto;
import com.project.underline.user.web.dto.UserReferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long id) {
        UserProfileDto profileDto = userProfileService.getUserProfile(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.SUCCESS, profileDto), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<FollowUserInfoDto> getFollowingList(@PathVariable Long id) {
        List<FollowUserInfoDto> followingList = userProfileService.getFollowingList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, followingList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/follower")
    public ResponseEntity<FollowUserInfoDto> getFollowerList(@PathVariable Long id) {
        List<FollowUserInfoDto> followerList = userProfileService.getFollowerList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, followerList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<UserPostDto> getUserPostList(@PathVariable Long id) {
        List<UserPostDto> postList = userProfileService.getUserPostList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, postList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/hashtag")
    public ResponseEntity<String> getUserHashtagList(@PathVariable Long id) {
        List<String> hashtagList = userProfileService.getUserHashtagList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, hashtagList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<String> getUserCategoryList(@PathVariable Long id) {
        List<String> categoryList = userProfileService.getUserCategoryList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, categoryList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/reference")
    public ResponseEntity<String> getUserReferenceList(@PathVariable Long id) {
        List<UserReferenceDto> referenceList = userProfileService.getUserReferenceList(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, referenceList), HttpStatus.OK
        );
    }
}
