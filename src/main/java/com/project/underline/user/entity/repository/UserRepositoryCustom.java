package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.project.underline.user.web.dto.UserProfileDto;
import com.project.underline.user.web.dto.UserReferenceDto;

import java.util.List;

public interface UserRepositoryCustom {

    UserProfileDto selectUserProfile(ProfileSearchCondition condition);

    List<FollowUserInfoDto> selectFollowingList(Long id);

    List<FollowUserInfoDto> selectFollowerList(Long id);

    List<UserPostDto> selectUserPostList(Long id);

    List<String> selectUserHashtagList(Long id);

    List<String> selectUserCategoryList(Long id);

    List<UserReferenceDto> selectUserReferenceList(Long id);

    List<UserProfileDto> searchUserProfile(String keyword);
}
