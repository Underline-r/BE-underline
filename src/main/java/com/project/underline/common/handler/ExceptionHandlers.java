package com.project.underline.common.handler;

import com.project.underline.common.exception.BadRequestException;
import com.project.underline.common.exception.UnauthorizedException;
import com.project.underline.common.payload.Response;
import com.project.underline.common.payload.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UnauthorizedException.class)
    protected  <T> Response<T> unauthorizedException() {
        return ResponseFactory.createFail(HttpStatus.UNAUTHORIZED,"토큰토큰토큰");
    }

    @ExceptionHandler(BadRequestException.class)
    protected  <T> Response<T> customErrorException(BadRequestException ex) {
        return ResponseFactory.createFail(ex.getStatusCode(),ex.getMessage());
    }

}
