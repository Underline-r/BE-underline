package com.project.underline;

import com.project.underline.common.metadata.LaguageType;
import com.project.underline.common.metadata.StatusMessage;
import com.project.underline.common.payload.Response;
import com.project.underline.common.payload.ResponseFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommonTestController {

    @GetMapping("/test")
    public void testMethod() {
        System.out.println(StatusMessage.SUCCESS.getMessage(200));
    }

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
