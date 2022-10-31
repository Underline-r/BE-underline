package com.project.underline.user.web;

import com.project.underline.common.jwt.TokenDto;
import com.project.underline.user.service.UserService;
import com.project.underline.user.web.dto.LoginRequestDto;
import com.project.underline.user.web.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long createUser(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.createUser(signupRequestDto);
    }

    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto){
        TokenDto login = userService.login(loginRequestDto);
        return ResponseEntity.ok(login);
    }
}
