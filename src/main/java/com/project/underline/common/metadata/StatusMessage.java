package com.project.underline.common.metadata;

public enum StatusMessage {
    SUCCESS(200,"success","success"),
    TOKEN_OVER(401,"토큰이 만료되었습니다","token is over"),
    UNAUTHORIZED(403,"인가되지 않은 사용자입니다","This user is not authorized"),
    ;

    private int statusCode;
    private String krMessage;
    private String enMessage;

    StatusMessage(int statusCode, String krMessage, String enMessage) {
        this.statusCode = statusCode;
        this.krMessage = krMessage;
        this.enMessage = enMessage;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String getMessage(int statusCode){
        // Refact. 매개변수로 받아주려했으나 사용자 헤더에서 값을 땡겨올것 같아서 일단은 korean으로 고정
        String languageSetting = LaguageType.KOREAN.getLaguageType();

        if(languageSetting.equals(LaguageType.ENGLISH.getLaguageType())){
            return enMessage;
        }else{
            return krMessage;
        }
    }
}
