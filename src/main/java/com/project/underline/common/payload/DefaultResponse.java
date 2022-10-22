package com.project.underline.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // error 처리 시 data : 없앰
public class DefaultResponse<T> {
    private Class exception;
    private int statusCode;
    private String message;
    private T data;

    public DefaultResponse(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int statusCode, final String message) {
        return res(statusCode, message, null);
    }

    public static<T> DefaultResponse<T> res(final int statusCode, final String message, final T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static<T> DefaultResponse<T> errRes(final int statusCode, final Class exception, final String message) {
        return DefaultResponse.<T>builder()
                .exception(exception)
                .statusCode(statusCode)
                .message(message)
                .build();
    }

}
