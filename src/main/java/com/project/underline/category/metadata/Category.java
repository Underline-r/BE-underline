package com.project.underline.category.metadata;

import lombok.Getter;

@Getter
public enum Category {
    // CAUTION. DB에는 code부분이 저장되어야합니다
    ART("art","예술","Art"),
    BIOGRAPHY("biography","전기","Biography"),
    BUSINESS("business","비즈니스","Business"),
    CHILDREN("children","어린이","Children"),
    COMICS("comics","만화","Comics"),
    COOK("cook","요리","Cook"),
    FANTASY("fantasy","판타지","Fantasy"),
    FICTION("fiction","소설","Fiction"),
    HISTORY("history","역사","History"),
    HORROR("horror","공포","Horror"),
    MUSIC("music","음악","Music"),
    MYSTERY("mystery","미스테리","Mystery"),
    POETRY("poetry","시","Poetry"),
    PSYCHOLOGY("psychology","심리학","Psychology"),
    ROMANCE("romance","로맨스","Romance"),
    SCIENCE("science","과학","Science"),
    SCIENCE_FICTION("scienceFiction","SF","Science fiction"),
    SPORTS("sports","스포츠","Sports"),
    THRILLER("thriller","스릴러","Thriller"),
    DRAMA("drama","드라마","Drama"),
    TRAVEL("travel","여행","Travel"),
    HUMOR("humor","유머","Humor"),
    COMEDY("comedy","코미디","Comedy"),
    CONTEMPORARY("contemporary","현대의","Contemporary"),
    CLASSICS("classics","고전의","Classics"),
    FAMILY("family","가족","Family"),
    GROWTH("growth","성장","Growth"),
    SURVIVAL("survival","생존","Survival"),
    WEALTH("wealth","부","Wealth"),
    HEALING("healing","힐링","Healing"),
    LOVE("love","사랑","Love"),
    FRIENDSHIP("friendship","우정","Friendship")
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
