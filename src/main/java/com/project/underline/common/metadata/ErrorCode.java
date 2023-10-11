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
    CANNOT_CONVERT_FILE(BAD_REQUEST, "파일 변환이 실패하였습니다."),
    CANNOT_FOUND_POST(BAD_REQUEST, "존재하지 않는 게시글입니다"),
    WRONG_APPROACH(BAD_REQUEST,"잘못된 접근입니다."),
    DUP_EMAIL(BAD_REQUEST,"이미 사용 중인 이메일 입니다."),
    INVALID_EMAIL(BAD_REQUEST,"이메일 형식이 올바르지 않습니다.."),
    INVALID_SHARE_TARGET(BAD_REQUEST,"옳지 않은 공유 대상입니다."),
    CATEGORY_LIMIT(BAD_REQUEST, "카테고리는 3개까지만 등록 가능합니다."),
    NO_SUCH_USER(BAD_REQUEST,"등록된 유저가 아닙니다."),
    INVALID_PASSWORD(BAD_REQUEST,"패스워드 형식이 올바르지 않습니다."),
    MISMATCH_PASSWORD(BAD_REQUEST,"패스워드가 일치하지 않습니다."),
    NO_POST_DATA(BAD_REQUEST,"존재하지 않는 게시글입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
    }
