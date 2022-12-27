package com.project.underline.user.web;

import com.project.underline.category.web.dto.UserCategoryListRequest;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.user.service.MyInfoService;
import com.project.underline.user.web.dto.SignupRequestDto;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-info")
public class MyInfoController {

    private final MyInfoService infoService;

    @PatchMapping()
    public ResponseEntity changeUserProfile(@RequestBody UserProfileDto profileDto) {
        infoService.changeUserProfile(profileDto);

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

    @PatchMapping("/category")
    public ResponseEntity changeUserCategory(@RequestBody UserCategoryListRequest category) {
        infoService.changeUserCategory(category.getCategory());

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
    public ResponseEntity regUserProfileImage() {
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS), HttpStatus.OK
        );
    }

}