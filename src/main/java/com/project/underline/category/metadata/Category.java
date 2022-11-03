package com.project.underline.category.metadata;

import lombok.Getter;

@Getter
public enum Category {
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
