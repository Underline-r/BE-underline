package com.project.underline.bookmark.web.controller;

import com.project.underline.bookmark.web.dto.BookmarkRequest;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test")
    public ResponseEntity test() {
        BookmarkRequest bookmarkRequest = new BookmarkRequest();

        String temp = "";
        // 예외에 따른 분기
        if ("".equals(temp)) {
            throw new IllegalArgumentException(ResponseMessage.NOT_FOUND_USER);
        }

        return new ResponseEntity(
                // class + final String + Constructor
//                new DefaultRes<>(StatusCode.OK, ResponseMessage.CREATED_USER, bookmarkRequest), HttpStatus.OK

                // class + final String + builder
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.CREATED_USER, bookmarkRequest), HttpStatus.OK
        );
    }

}
