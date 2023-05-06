package com.project.underline.category.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserCategoryListRequest {
    private List<String> codes;
}
