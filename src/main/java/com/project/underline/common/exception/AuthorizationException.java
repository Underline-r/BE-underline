package com.project.underline.common.exception;

import com.project.underline.common.metadata.StatusMessage;
import com.project.underline.common.payload.Response;
import org.jetbrains.annotations.NotNull;

public class AuthorizationException extends RuntimeException{

    public static <T> @NotNull Response<T> tokenOverException(){
        Response failResponse = new Response<>();

        failResponse.setStatusCode(StatusMessage.TOKEN_OVER.getStatusCode());
        failResponse.setMessage(StatusMessage.TOKEN_OVER.getMessage(StatusMessage.TOKEN_OVER.getStatusCode()));

        return failResponse;
    }

    public static <T> Response<T> unauthorizedException(){
        Response failResponse = new Response<>();

        failResponse.setStatusCode(StatusMessage.UNAUTHORIZED.getStatusCode());
        failResponse.setMessage(StatusMessage.UNAUTHORIZED.getMessage(StatusMessage.UNAUTHORIZED.getStatusCode()));

        return failResponse;
    }
}
