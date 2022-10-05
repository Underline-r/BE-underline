package com.project.underline.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // error 처리 시 data : 없앰
public class DefaultRes<T> {
    private Class exception;
    private int statusCode;
    private String message;
    private T data;

    public DefaultRes(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultRes<T> res(final int statusCode, final String message) {
        return res(statusCode, message, null);
    }

    public static<T> DefaultRes<T> res(final int statusCode, final String message, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static<T> DefaultRes<T> errRes(final int statusCode, final Class exception, final String message) {
        return DefaultRes.<T>builder()
                .exception(exception)
                .statusCode(statusCode)
                .message(message)
                .build();
    }

}
