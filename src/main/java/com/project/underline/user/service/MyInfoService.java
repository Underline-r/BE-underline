package com.project.underline.user.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import com.project.underline.category.service.CategoryService;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.repository.CommentRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.CommentResponse;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyInfoService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final UserCategoryRelationRepository userCategoryRelationRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void changeUserProfile(UserProfileDto dto) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        User findUser = userService.existUser(currentUserId);
        findUser.changeProfile(dto.getNickname(), dto.getDescription(), dto.getImagePath());

        userRepository.save(findUser);
    }

    public void changeUserCategory(List<String> category) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        User findUser = userService.existUser(currentUserId);

        userCategoryRelationRepository.deleteAllByUserId(currentUserId);

        for (String eachCategory : category) {
            categoryService.checkCategoryConsistency(eachCategory);
            UserCategoryRelation userCategoryRelation = new UserCategoryRelation(eachCategory);
            userCategoryRelation.addUser(findUser);
            userCategoryRelationRepository.save(userCategoryRelation);
        }
    }

    /**
     * TODO: 변경된 비밀번호를 가지고 재로그인 요청 ?
     */
    public void changeUserPassword(String newPassword) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        User findUser = userService.existUser(currentUserId);
        findUser.changePassword(passwordEncoder.encode(newPassword));
        userRepository.save(findUser);
    }

    public void regUserProfileImage() {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        User findUser = userService.existUser(currentUserId);
    }

    public CommentResponse listComments() {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        User findUser = userService.existUser(currentUserId);
        return new CommentResponse(commentRepository.findAllByUser(findUser));
    }

    /*public List<UserPostDto> listLikePost() {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        User findUser = userService.existUser(currentUserId);

        return postRepository.findAllByMyPick(findUser);
    }*/
}
