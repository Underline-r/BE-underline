package com.project.underline.user.web;

import com.project.underline.common.jwt.TokenDto;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.metadata.StatusCode;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.user.service.UserService;
import com.project.underline.user.web.dto.LoginRequestDto;
import com.project.underline.user.web.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Long> createUser(@RequestBody SignupRequestDto signupRequestDto) {
        Long createdUser = userService.createUser(signupRequestDto);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, ResponseMessage.CREATED_USER, createdUser), HttpStatus.OK
        );
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto){
        TokenDto tokenDto = userService.login(loginRequestDto);
        return new ResponseEntity(
                DefaultResponse.res(StatusCode.OK, "로그인 되었습니다.", tokenDto), HttpStatus.OK
        );
    }
}
