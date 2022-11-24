package com.project.underline.feed.web.controller;


import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.feed.service.WebFeedService;
import com.project.underline.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class WebFeedController {
    private final WebFeedService webFeedService;


    @GetMapping("/feed")
    public ResponseEntity giveTimeline(Pageable pageable){

        // TO-DO. queryDsl로 리팩토링 필요
        Page<Post> webFeed =  webFeedService.makeWebFeed(pageable);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(webFeed)
                        .build()
                , HttpStatus.OK);
    }

}
