package com.project.underline.common.payload;

import com.project.underline.common.metadata.LaguageType;
import com.project.underline.common.metadata.StatusMessage;
import org.springframework.http.HttpStatus;

public class ResponseFactory {

    public static <T> Response<T> createSuccess(){
        return ResponseFactory.createSuccess(null);
    }

    public static <T> Response<T> createSuccess(T data){
        return ResponseFactory.createSuccess(data, StatusMessage.SUCCESS.getStatusCode());
    }

    public static <T> Response<T> createSuccess(T data, HttpStatus statusCode){
        return ResponseFactory.createSuccess(data,statusCode, StatusMessage.SUCCESS.getMessage(statusCode));
    }

    public static <T> Response<T> createSuccess(T data,HttpStatus statusCode, String message){

        Response<T> response = new Response<>();

        if (data != null){
            response.setData(data);
        }

        response.setMessage(message);
        response.setStatusCode(statusCode);

        return response;
    }

    public static <T> Response<T> createFail(HttpStatus statusCode, String message){

        Response<T> response = new Response<>();

        response.setMessage(message);
        response.setStatusCode(statusCode);

        return response;
    }
}
