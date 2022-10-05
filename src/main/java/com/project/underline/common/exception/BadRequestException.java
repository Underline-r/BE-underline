package com.project.underline.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private HttpStatus statusCode;
    private String message;
}
