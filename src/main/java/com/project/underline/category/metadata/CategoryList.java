package com.project.underline.category.metadata;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryList {
    Category[] categoryCodeList = Category.values();

    // key -> code , value -> message형식으로 {code:value, code:value .. } 이런식으로 return하고 클라이언트에서 줄땐 다시 code값으로 넘겨줘야함
    // To-do. code로 넘겨줘야한다고 알려주기
    public static final HashMap<String, String> categoryKoreanMessageMap = new HashMap<String, String> ();
    public static final HashMap<String, String> categoryEnglishMessageMap = new HashMap<String, String>();

    @PostConstruct
    void categoryMap(){
        for (Category category :categoryCodeList) {
            categoryKoreanMessageMap.put(category.getCode(),category.getKoreanMessage());
            categoryEnglishMessageMap.put(category.getCode(),category.getEnglishMessage());
        }
    }

    // To-do. 지금은 그냥 korean을 넘겨주지만 나중에 언어패치해줘야함
    public static HashMap<String, String> getCategoryList(){
        HashMap<String, String> categoryList = categoryKoreanMessageMap;
        return categoryList;
    }
}
