package com.project.underline.category.web.controller;

import com.project.underline.category.metadata.CategoryList;
import com.project.underline.category.service.CategoryService;
import com.project.underline.common.metadata.StatusCode;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.project.underline.common.metadata.ResponseMessage.SUCCESS;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    CategoryService categoryService;
    UserRepository userRepository;

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity registerFavoriteCategory(
            @RequestBody List<String> category,
            @AuthenticationPrincipal UserDetails userDetails){

        /* To-do. DB에 저장된 userId값이 필요한건데 name밖에 못가져옴 한방에 id값을 가져올 방법은? */
        String userName = userDetails.getUsername();
        Optional<User> user = userRepository.findByEmail(userName);
        Long userId = user.get().getId();

        categoryService.registerFavoriteCategory(userId,category);

        return new ResponseEntity(
                DefaultResponse.builder()
                        .statusCode(StatusCode.OK)
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
                        .statusCode(StatusCode.OK)
                        .message(SUCCESS)
                        .data(categoryList)
                        .build()
                , HttpStatus.OK
        );
    }
}
