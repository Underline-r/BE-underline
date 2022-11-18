package com.project.underline.category.metadata;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

@Configuration
public class CategoryList {
    private static Category[] categoryValueList = Category.values();

    // key -> code , value -> message형식으로 {code:value, code:value .. } 이런식으로 return하고 클라이언트에서 줄땐 다시 code값으로 넘겨줘야함
    // To-do. code로 넘겨줘야한다고 알려주기
    public static final HashMap<String, String> categoryKoreanMessageMap = new HashMap<String, String> ();
    public static final HashMap<String, String> categoryEnglishMessageMap = new HashMap<String, String>();
    public static final ArrayList<String> categoryCodeList = new ArrayList<String>();
    @PostConstruct
    public static void categoryMap(){
        for (Category category :categoryValueList) {
            categoryKoreanMessageMap.put(category.getCode(),category.getKoreanMessage());
            categoryEnglishMessageMap.put(category.getCode(),category.getEnglishMessage());
            categoryCodeList.add(category.getCode());
        }
    }

    // To-do. 지금은 그냥 korean을 넘겨주지만 나중에 언어패치해줘야함
    public static HashMap<String, String> getCategoryList(){
        HashMap<String, String> categoryList = categoryKoreanMessageMap;
        return categoryList;
    }
}
