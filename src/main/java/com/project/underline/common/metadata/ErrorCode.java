package com.project.underline.common.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    INVALID_TOKEN(BAD_REQUEST, "토큰이 유효하지 않습니다"),
    MISMATCH_TOKEN(BAD_REQUEST, "토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),
    CANNOT_FOUND_USER(BAD_REQUEST, "해당 유저를 찾을 수 없습니다."),
    CANNOT_FOUND_POST(BAD_REQUEST, "존재하지 않는 게시글입니다"),
    WRONG_APPROACH(BAD_REQUEST,"잘못된 접근입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
    }
