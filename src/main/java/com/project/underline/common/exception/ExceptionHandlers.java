package com.project.underline.common.exception;

import com.project.underline.common.metadata.StatusCode;
import com.project.underline.common.payload.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception의 전역 처리
 */
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DefaultResponse> commonException(NullPointerException e) {
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(StatusCode.BAD_REQUEST, e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
