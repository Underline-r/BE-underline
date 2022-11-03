package com.project.underline.category.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    UserCategoryRelationRepository userCategoryRelationRepository;

    public void registerFavoriteCategory(Long userId, List<String> category) throws RuntimeException{
        List<UserCategoryRelation> userLikedCategoryList = new ArrayList<UserCategoryRelation>();

        /* To-do. 클라이언트에서 온 category가 Enum list에 있는 값이 아닐수도 있으니까 검증로직 추후 추가 */
        for (String eachCategory : category) {
            userLikedCategoryList.add(new UserCategoryRelation(userId,eachCategory));
        }

        userCategoryRelationRepository.saveAll(userLikedCategoryList);
    }

}
