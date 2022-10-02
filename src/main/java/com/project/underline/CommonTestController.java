package com.project.underline;

import com.project.underline.common.exception.TokenOverException;
import com.project.underline.common.exception.UnauthorizedException;
import com.project.underline.common.metadata.StatusMessage;
import com.project.underline.common.payload.Response;
import com.project.underline.common.payload.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonTestController {


    @GetMapping("/test")
    public void testMethod() {
        System.out.println(StatusMessage.SUCCESS.getMessage(HttpStatus.OK));
    }

    @GetMapping("/success")
    public <T> Response<T> createSuccessCase() {
        return ResponseFactory.createSuccess();
    }

    @GetMapping("/unauthorized")
    public <T> Response<T> unauthorizedCase() {
        throw new UnauthorizedException();
    }

    @GetMapping("/tokenover")
    public <T> Response<T> tokenOverCase() {
        throw new TokenOverException();
    }


}
