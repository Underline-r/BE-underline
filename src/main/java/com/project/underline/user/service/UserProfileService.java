package com.project.underline.user.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProfileService {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserProfileDto getUserProfile(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        ProfileSearchCondition profileSearchCondition = new ProfileSearchCondition(checkedUser.getId(), SecurityUtil.getCurrentUserId());

        UserProfileDto profileDto = userRepository
                .selectUserProfile(profileSearchCondition);

        if (profileUserId.equals(SecurityUtil.getCurrentUserId())) {
            profileDto.setMyPage(true);
        }

        return profileDto;
    }

    public List<FollowUserInfoDto> getFollowingList(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        return userRepository.selectFollowingList(checkedUser.getId());
    }

    public List<FollowUserInfoDto> getFollowerList(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        return userRepository.selectFollowerList(checkedUser.getId());
    }

    public List<UserPostDto> getUserPostList(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        return userRepository.selectUserPostList(checkedUser.getId());
    }

    public List<String> getUserHashtagList(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        return userRepository.selectUserHashtagList(checkedUser.getId());
    }

    public List<String> getUserCategoryList(Long profileUserId) {
        User checkedUser = userService.existUser(profileUserId);
        return userRepository.selectUserCategoryList(checkedUser.getId());
    }
}