package com.project.underline.common.exception;

import com.project.underline.common.metadata.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "This user is not authorized")
public class UnauthorizedException extends RuntimeException {

    // 어노테이션에 변수 어떻게 할당해주는겨?
    private static final String notFound = StatusMessage.FORBIDDEN.getMessage(HttpStatus.FORBIDDEN);

    public UnauthorizedException() {
        super(notFound);
    }



}
