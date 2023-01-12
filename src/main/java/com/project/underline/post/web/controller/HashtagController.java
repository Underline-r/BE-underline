package com.project.underline.post.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.HashtagService;
import com.project.underline.post.web.dto.HashtagSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class HashtagController {
    private final HashtagService hashtagService;

    @GetMapping("/hashtag-search")
    public ResponseEntity searchBasedOnHashtag(@RequestParam("keyword") String keyword, @RequestParam("user-nickname") String userNickname){

        HashtagSearchResponse hashtagSearchResponse = hashtagService.searchBasedOnHashtag(keyword,userNickname);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(hashtagSearchResponse)
                        .build()
                , HttpStatus.OK);
    }
}
