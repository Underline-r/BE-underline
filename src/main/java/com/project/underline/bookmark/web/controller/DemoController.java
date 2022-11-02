package com.project.underline.bookmark.web.controller;

import com.project.underline.bookmark.web.dto.BookmarkRequest;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.metadata.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /**
     * TODO: test API 로 권한 테스트를 해보았는데, 권한이 없을 경우 예외처리도 다시 생각해야함.
     */
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
                DefaultResponse.res(StatusCode.OK, ResponseMessage.CREATED_USER, bookmarkRequest), HttpStatus.OK
        );
    }

}
