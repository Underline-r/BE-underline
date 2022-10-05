package com.project.underline.common;

import com.project.underline.common.metadata.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception의 전역 처리
 */
@ControllerAdvice
public class DemoControllerAdvisor {
    // Exception.class
    @ExceptionHandler()
    public ResponseEntity<DefaultRes> test(IllegalArgumentException e) {
        return new ResponseEntity<DefaultRes>(
                DefaultRes.errRes(StatusCode.BAD_REQUEST, e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler()
    public ResponseEntity<DefaultRes> test2(NullPointerException e) {
        return new ResponseEntity<DefaultRes>(
                DefaultRes.errRes(StatusCode.BAD_REQUEST, e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
