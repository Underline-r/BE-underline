package com.project.underline.common.exception;

import com.project.underline.common.metadata.ErrorCode;
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
    public ResponseEntity<DefaultResponse> nullPointerException(NullPointerException e) {
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(), e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnderlineException.class)
    public ResponseEntity<DefaultResponse> underlineException(UnderlineException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(errorCode.getHttpStatus().value(),
                        e.getClass(), errorCode.getMessage()), errorCode.getHttpStatus());
    }


}
