package com.project.underline.post.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.PickService;
import com.project.underline.post.web.dto.PickRequest;
import com.project.underline.post.web.dto.PickedUserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PickController {
    private final PickService pickService;

    @PostMapping("/pick")
    public ResponseEntity checkUserPostPick(@RequestBody PickRequest pickRequest){

        pickService.checkUserPostPick(pickRequest);
        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/pick-users")
    public ResponseEntity inquiryPickedUser(@PathVariable Long postId){

        PickedUserListResponse pickedUserListResponse = pickService.inquiryPickedUser(postId);
        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(OK.value())
                        .message(SUCCESS)
                        .data(pickedUserListResponse)
                        .build()
                , HttpStatus.OK);
    }


}
