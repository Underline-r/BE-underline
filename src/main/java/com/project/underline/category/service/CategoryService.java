package com.project.underline.category.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.underline.category.metadata.CategoryList.categoryCodeList;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final UserCategoryRelationRepository userCategoryRelationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerFavoriteCategory(List<String> category){

        Long currentUserId = SecurityUtil.getCurrentUserId();

        User findUser = userRepository.findById(currentUserId).get();

        if(category.size()>3){
            throw new UnderlineException(ErrorCode.CATEGORY_LIMIT);
        }

        /* Todo. 클라이언트에서 온 category가 Enum list에 있는 값이 아닐수도 있으니까 검증로직 추후 추가 */
        for (String eachCategory : category) {
            checkCategoryConsistency(eachCategory);
            UserCategoryRelation userCategoryRelation = new UserCategoryRelation(eachCategory);
            userCategoryRelation.addUser(findUser);
            userCategoryRelationRepository.save(userCategoryRelation);
        }
    }

    public void checkCategoryConsistency(String category){
        for(String str : categoryCodeList) {
            if(str.equals(category)) {
                return;
            }
        }
        throw new IllegalArgumentException();

    }

    @Transactional
    public void reorganizeFavoriteCategory(List<String> category) {
        userCategoryRelationRepository.deleteAllByUserId(SecurityUtil.getCurrentUserId());
        registerFavoriteCategory(category);
    }
}
