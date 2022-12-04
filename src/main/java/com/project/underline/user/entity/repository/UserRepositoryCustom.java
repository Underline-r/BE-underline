package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.UserProfileDto;

public interface UserRepositoryCustom {

    UserProfileDto getUserProfile(ProfileSearchCondition condition);
}
