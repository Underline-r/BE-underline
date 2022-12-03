package com.project.underline.notification.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.notification.service.NotificationService;
import com.project.underline.notification.web.dto.NotificationCheckRequest;
import com.project.underline.notification.web.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;


    @GetMapping("/notification")
    public ResponseEntity inquiryNotification(){

        List<NotificationResponse> notifications = notificationService.inquiryNotification();

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(notifications)
                        .build()
                , HttpStatus.OK);
    }

    @PutMapping("/notification-count")
    public ResponseEntity checkNotification(@RequestBody NotificationCheckRequest notificationCheckRequest){

        notificationService.checkNotification(notificationCheckRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }
}
