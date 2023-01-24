package com.project.underline.bookmark.web.controller;

import com.project.underline.bookmark.service.BookmarkService;
import com.project.underline.bookmark.web.dto.BookmarkRequest;
import com.project.underline.bookmark.web.dto.BookmarkResponse;
import com.project.underline.common.payload.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity registerBookmark(@RequestBody BookmarkRequest bookmarkRequest){
        bookmarkService.registerBookmark(bookmarkRequest);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/bookmark")
    public ResponseEntity inquiryBookmarks(){

        BookmarkResponse bookmarkResponse = bookmarkService.inquiryBookmarks();

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(bookmarkResponse)
                        .build()
                , HttpStatus.OK);
    }

}
