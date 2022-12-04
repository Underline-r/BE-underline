package com.project.underline.common.exception;

import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.payload.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
    * SQL error handle
    * TODO : 어떤 정보까지 클라이언트에게 공유하고, 서버에 남길 것인지 회의
    * */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<DefaultResponse> sqlException(DataAccessException e) {
        log.error(e.getMessage());
        return new ResponseEntity<DefaultResponse>(
                DefaultResponse.errRes(HttpStatus.BAD_REQUEST.value(),
                        e.getClass(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
