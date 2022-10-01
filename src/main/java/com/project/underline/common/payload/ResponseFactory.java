package com.project.underline.common.payload;

import com.project.underline.common.metadata.LaguageType;
import com.project.underline.common.metadata.StatusMessage;

public class ResponseFactory {

    public static <T> Response<T> createSuccess(){
        return ResponseFactory.createSuccess(null);
    }

    public static <T> Response<T> createSuccess(T data){
        return ResponseFactory.createSuccess(data, StatusMessage.SUCCESS.getStatusCode());
    }

    public static <T> Response<T> createSuccess(T data, int statusCode){
        return ResponseFactory.createSuccess(data,statusCode, StatusMessage.SUCCESS.getMessage(statusCode));
    }

    public static <T> Response<T> createSuccess(T data,int statusCode, String message){

        Response<T> response = new Response<>();

        if (data != null){
            response.setData(data);
        }

        response.setMessage(message);
        response.setStatusCode(statusCode);

        return response;
    }
}
