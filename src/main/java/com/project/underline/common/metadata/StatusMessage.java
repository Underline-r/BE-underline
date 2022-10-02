package com.project.underline.common.metadata;

import org.springframework.http.HttpStatus;

public enum StatusMessage {
    SUCCESS(HttpStatus.OK,"success","success"),
    TOKEN_OVER(HttpStatus.UNAUTHORIZED,"토큰이 만료되었습니다","token is over"),
    FORBIDDEN(HttpStatus.FORBIDDEN,"인가되지 않은 사용자입니다","This user is not authorized"),
    ;

    private HttpStatus statusCode;
    private String krMessage;
    private String enMessage;

    StatusMessage(HttpStatus statusCode, String krMessage, String enMessage) {
        this.statusCode = statusCode;
        this.krMessage = krMessage;
        this.enMessage = enMessage;
    }

    public HttpStatus getStatusCode(){
        return statusCode;
    }

    public String getMessage(HttpStatus statusCode){
        // Refact. 매개변수로 받아주려했으나 사용자 헤더에서 값을 땡겨올것 같아서 일단은 korean으로 고정
        String languageSetting = LaguageType.KOREAN.getLaguageType();

        if(languageSetting.equals(LaguageType.ENGLISH.getLaguageType())){
            return enMessage;
        }else{
            return krMessage;
        }
    }
}
