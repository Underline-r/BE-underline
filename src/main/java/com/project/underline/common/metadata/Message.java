package com.project.underline.common.metadata;

public enum Message {
    SUCCESS(200,"success","success"),
    TOKEN_OVER(401,"토큰이 만료되었습니다","token is over"),
    UNAUTHORIZED(401,"인가되지 않은 사용자입니다","This user is not authorized"),
    ;

    private int statusCode;
    private String krMessage;
    private String enMessage;

    Message(int statusCode, String krMessage, String enMessage) {
        this.statusCode = statusCode;
        this.krMessage = krMessage;
        this.enMessage = enMessage;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String getMessage(int statusCode, String languageSetting){
        if(languageSetting.equals(LaguageType.ENGLISH.getLaguageType())){
            return enMessage;
        }else{
            return krMessage;
        }
    }
}
