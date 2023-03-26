package com.project.underline.category.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class CategoryResponse {
    private ArrayList<HashMap<String,String>> categories;

    public CategoryResponse(){
    }
}
