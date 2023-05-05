package com.project.underline.common.exception;

import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.payload.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception의 전역 처리
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DefaultResponse> nullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(), e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnderlineException.class)
    public ResponseEntity<DefaultResponse> underlineException(UnderlineException e) {
        ErrorCode errorCode = e.getErrorCode();
        // 내부 exception의 stacktrace 찍기 추가
        log.error(errorCode.getMessage(), e);

        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(errorCode.getHttpStatus().value(),
                        e.getClass(), errorCode.getMessage()), errorCode.getHttpStatus());
    }

    /*
    * SQL error handle
    * */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<DefaultResponse> sqlException(DataAccessException e) {

        log.error(e.getMessage(), e);
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(),
                        e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> contentValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(),
                        e.getClass(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

}
