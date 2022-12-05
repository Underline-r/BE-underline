package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.UserDto;
import com.project.underline.user.web.dto.UserProfileDto;

import java.util.List;

public interface UserRepositoryCustom {

    UserProfileDto getUserProfile(ProfileSearchCondition condition);

    List<UserDto> getFollowingList(Long id);

    List<UserDto> getFollowerList(Long id);
}
