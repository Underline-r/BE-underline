package com.project.underline.common.metadata;

import lombok.Getter;

@Getter
public enum ResponseMessage2 {
     LOGIN_SUCCESS("로그인 성공"),
     LOGIN_FAIL("로그인 실패"),
     READ_USER("회원 정보 조회 성공"),
     NOT_FOUND_USER("회원을 찾을 수 없습니다."),
     CREATED_USER("회원 가입 성공"),
     UPDATE_USER("회원 정보 수정 성공"),
     DELETE_USER("회원 탈퇴 성공");

     private final String value;

    ResponseMessage2(String value) {
        this.value = value;
    }
}
