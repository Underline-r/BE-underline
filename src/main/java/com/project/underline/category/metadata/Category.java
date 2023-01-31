package com.project.underline.category.metadata;

import lombok.Getter;

@Getter
public enum Category {
    // CAUTION. DB에는 code부분이 저장되어야합니다
    BOOK("book","책","Book"),
    LIFE("life","인생","Life"),
    SADNESS("sadness","슬픔","Sad Emotions")
    ;

    private final String code;
    private final String koreanMessage;
    private final String englishMessage;

    Category(String code, String koreanMessage, String englishMessage) {
        this.code = code;
        this.koreanMessage = koreanMessage;
        this.englishMessage = englishMessage;
    }
}
