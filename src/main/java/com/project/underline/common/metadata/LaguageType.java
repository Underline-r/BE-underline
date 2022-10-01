package com.project.underline.common.metadata;

public enum LaguageType {
    ENGLISH("english"),
    KOREAN("korean");

    private final String laguageType;

    LaguageType(String laguageType) {
        this.laguageType = laguageType;
    }

    public String getLaguageType(){
        return laguageType;
    }
}
