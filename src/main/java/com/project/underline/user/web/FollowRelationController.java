package com.project.underline.user.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.service.FollowRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowRelationController {

    @Autowired
    FollowRelationService followRelationService;

    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<String> follow(@PathVariable Long toUserId){
        followRelationService.follow(toUserId, SecurityUtil.getCurrentUserId());

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.FOLLOW, "팔로우 성공"), HttpStatus.OK
        );


    }

    @DeleteMapping("/unfollow/{toUserId}")
    public ResponseEntity<String> unfollow(@PathVariable Long toUserId){
        followRelationService.unfollow(toUserId, SecurityUtil.getCurrentUserId());

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.UNFOLLOW, "언팔로우 성공"), HttpStatus.OK
        );
    }
}