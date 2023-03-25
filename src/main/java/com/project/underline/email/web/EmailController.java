package com.project.underline.email.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.email.service.EmailService;
import com.project.underline.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    @PostMapping("/verify-email")
    public ResponseEntity<DefaultResponse> verifyEmail(@RequestBody MailDto dto){
        userService.validationEmail(dto.getEmail());
        try {
            String checkNumber = emailService.sendSimpleMessage(dto);
            return new ResponseEntity(
                    DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.SUCCESS, checkNumber), HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(),
                            e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
