package com.project.underline.user.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.service.UserProfileService;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-info")
public class MyInfoController {

    private final UserProfileService userProfileService;

    @PatchMapping()
    public ResponseEntity changeUserProfile(@RequestBody UserProfileDto profileDto) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        userProfileService.changeUserProfile(currentUserId, profileDto);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PatchMapping("/category")
    public ResponseEntity changeUserCategory() {
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PatchMapping("/password")
    public ResponseEntity changeUserPassword() {
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PostMapping("/image")
    public ResponseEntity regUserProfileImage() {
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

}
