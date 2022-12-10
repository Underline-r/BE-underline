package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserProfileDto;

import java.util.List;

public interface UserRepositoryCustom {

    UserProfileDto getUserProfile(ProfileSearchCondition condition);

    List<FollowUserInfoDto> getFollowingList(Long id);

    List<FollowUserInfoDto> getFollowerList(Long id);
}
