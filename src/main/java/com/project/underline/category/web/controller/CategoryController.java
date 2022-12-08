package com.project.underline.category.web.controller;

import com.project.underline.category.metadata.CategoryList;
import com.project.underline.category.service.CategoryService;
import com.project.underline.category.web.dto.UserCategoryListRequest;
import com.project.underline.common.payload.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity registerFavoriteCategory(
                @RequestBody UserCategoryListRequest category){

        /* To-do. DB에 저장된 userId값이 필요한건데 name밖에 못가져옴 한방에 id값을 가져올 방법은?
        *  re : Long currentUserId = SecurityUtil.getCurrentUserId(); 와 같은 방법으로 뽑아낼 수 있게 해둠
        *  */
        categoryService.registerFavoriteCategory(category.getCategory());

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(SUCCESS)
                        .build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/category-list")
    public ResponseEntity categoryList(){
        HashMap<String, String> categoryList = CategoryList.getCategoryList();

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(SUCCESS)
                        .data(categoryList)
                        .build()
                , HttpStatus.OK
        );
    }
}
