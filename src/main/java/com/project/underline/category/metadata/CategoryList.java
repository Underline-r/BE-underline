package com.project.underline.category.metadata;

import com.project.underline.category.web.dto.CategoryResponse;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class CategoryList {
    private static Category[] categoryValueList = Category.values();

    // key -> code , value -> message형식으로 {code:value, code:value .. } 이런식으로 return하고 클라이언트에서 줄땐 다시 code값으로 넘겨줘야함
    // Todo. code로 넘겨줘야한다고 알려주기
//    public static final HashMap<String, String> categoryKoreanMessageMap = new HashMap<String, String> ();
//    public static final HashMap<String, String> categoryEnglishMessageMap = new HashMap<String, String>();
    public static final ArrayList<String> categoryCodeList = new ArrayList<String>();

    public static final ArrayList<HashMap<String,String>> categoryCodeResponse= new ArrayList<>();

    @PostConstruct
    public static void categoryMap(){
        for (Category category :categoryValueList) {
            HashMap<String,String> categoryMap = new HashMap<>();
            categoryMap.put("ko_name", category.getKoreanMessage());
            categoryMap.put("code", category.getCode());
            categoryCodeResponse.add(categoryMap);

            categoryCodeList.add(category.getCode());
        }
    }

    // Todo. 지금은 그냥 korean을 넘겨주지만 나중에 언어패치해줘야함
    public static CategoryResponse getCategoryList(){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categoryCodeResponse);
        return categoryResponse;
    }
}
