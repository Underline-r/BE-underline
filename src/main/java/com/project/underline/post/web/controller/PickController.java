package com.project.underline.post.web.controller;

import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.service.PickService;
import com.project.underline.post.web.dto.PickRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
