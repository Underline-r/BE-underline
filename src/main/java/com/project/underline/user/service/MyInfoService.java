package com.project.underline.user.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.common.util.SecurityUtil;
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
    private final PasswordEncoder passwordEncoder;

    public void changeUserProfile(UserProfileDto dto) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        User findUser = userService.existUser(currentUserId);
        findUser.changeProfile(dto.getNickname(), dto.getDescription());

        userRepository.save(findUser);
    }

    public void changeUserCategory(List<String> category) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        User findUser = userService.existUser(currentUserId);

        // TODO: category service 주입받지 말고 DDD로 개발하기
        List<UserCategoryRelation> userCategoryRelations = findUser.getUserCategoryRelations();
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
}
