package com.project.underline.common.exception;

import com.project.underline.common.metadata.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnderlineException extends RuntimeException {
    private final ErrorCode errorCode;
}
