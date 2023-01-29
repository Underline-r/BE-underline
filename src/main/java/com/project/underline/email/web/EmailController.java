package com.project.underline.email.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.email.service.EmailService;
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

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody MailDto dto) {
        String checkNumber = emailService.sendSimpleMessage(dto);
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.SUCCESS, checkNumber), HttpStatus.OK
        );
    }

}
