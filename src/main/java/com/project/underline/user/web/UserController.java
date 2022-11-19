package com.project.underline.user.web;

import com.project.underline.common.jwt.TokenDto;
import com.project.underline.common.jwt.TokenRequestDto;
import com.project.underline.common.metadata.ResponseMessage;
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
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.CREATED_USER, createdUser), HttpStatus.OK
        );
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto){
        TokenDto tokenDto = userService.login(loginRequestDto);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), "로그인 되었습니다.", tokenDto), HttpStatus.OK
        );
    }

    /**
     * Access token이 만료되었을 경우 프론트에서 요청할 api
     * Refresh token을 Service Layer에서 검증 후 유효하지 않다면 재 로그인 유도
     * 유효하다면 바로 신규 토큰 발급
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = userService.refresh(tokenRequestDto);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), "토큰 갱신되었습니다.", tokenDto), HttpStatus.OK
        );
    }
}
