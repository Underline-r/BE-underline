package com.project.underline.feed.web.controller;


import com.project.underline.common.jwt.JwtFilter;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.feed.service.WebFeedService;
import com.project.underline.feed.web.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class WebFeedController {
    private final WebFeedService webFeedService;


    @GetMapping("/feed")
    public ResponseEntity giveTimeline(Pageable pageable){

        FeedResponse webFeed =  webFeedService.makeWebFeed(pageable);
        webFeed.isLastCheck();

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(webFeed)
                        .build()
                , HttpStatus.OK);
    }

}
