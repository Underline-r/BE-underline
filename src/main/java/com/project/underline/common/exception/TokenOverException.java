package com.project.underline.common.exception;

import com.project.underline.common.metadata.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "토큰이 만료되었습니다")
public class TokenOverException extends RuntimeException {

    private static final String forbidden = StatusMessage.FORBIDDEN.getMessage(HttpStatus.FORBIDDEN);

    public TokenOverException(){
        super(forbidden);
    }
}
