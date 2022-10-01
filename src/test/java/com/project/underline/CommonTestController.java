package com.project.underline;

import com.project.underline.common.payload.Response;
import com.project.underline.common.payload.ResponseFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonTestController {

    @GetMapping("/success")
    public <T> Response<T> createSuccessCase() {
        return ResponseFactory.createSuccess();
    }

    @GetMapping("/unauthorized")
    public <T> Response<T> unauthorizedCase() {
        return ResponseFactory.createSuccess();
    }

    @GetMapping("/tokenover")
    public <T> Response<T> tokenOverCase() {
        return ResponseFactory.createSuccess();
    }


}
