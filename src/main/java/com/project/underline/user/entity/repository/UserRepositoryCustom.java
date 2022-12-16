package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.*;

import java.util.List;

public interface UserRepositoryCustom {

    UserProfileDto selectUserProfile(ProfileSearchCondition condition);

    List<FollowUserInfoDto> selectFollowingList(Long id);

    List<FollowUserInfoDto> selectFollowerList(Long id);

    List<UserPostDto> selectUserPostList(Long id);

    List<String> selectUserHashtagList(Long id);

    List<String> selectUserCategoryList(Long id);
}
