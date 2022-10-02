package com.project.underline.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private HttpStatus statusCode;
    private String message;
    private T data;

}
