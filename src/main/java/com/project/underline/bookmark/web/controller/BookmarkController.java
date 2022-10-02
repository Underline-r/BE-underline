package com.project.underline.bookmark.web.controller;

import com.project.underline.bookmark.web.dto.BookmarkRequest;
import com.project.underline.common.DefaultRes;
import com.project.underline.common.metadata.ResponseMessage2;
import com.project.underline.common.metadata.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookmarkController {

    @GetMapping("/test")
    public ResponseEntity test() {
        BookmarkRequest bookmarkRequest = new BookmarkRequest();

        return new ResponseEntity(
                // enum + get value
                new DefaultRes<>(StatusCode.OK, ResponseMessage2.CREATED_USER.getValue(), bookmarkRequest), HttpStatus.OK

                // class + final String + Constructor
//                new DefaultRes<>(StatusCode.OK, ResponseMessage.CREATED_USER, bookmarkRequest), HttpStatus.OK

                // class + final String + builder
//                DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, bookmarkRequest), HttpStatus.OK
        );
    }

}
