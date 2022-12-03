package com.project.underline.user.web;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.user.service.UserProfileService;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/user/profile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long id) {
        UserProfileDto profileDto = userProfileService.getUserProfile(id);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), "조회되었습니다.", profileDto), HttpStatus.OK
        );
    }
}
