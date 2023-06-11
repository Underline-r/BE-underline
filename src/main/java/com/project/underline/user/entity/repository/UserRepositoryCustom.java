package com.project.underline.user.entity.repository;

import com.project.underline.search.web.dto.SearchUserDto;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.project.underline.user.web.dto.UserProfileDto;
import com.project.underline.user.web.dto.UserSourceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    UserProfileDto selectUserProfile(ProfileSearchCondition condition);

    List<FollowUserInfoDto> selectFollowingList(Long id);

    List<FollowUserInfoDto> selectFollowerList(Long id);

    List<UserPostDto> selectUserPostList(Long id);

    List<String> selectUserHashtagList(Long id);

    List<String> selectUserCategoryList(Long id);

    List<UserSourceDto> selectUserSourceList(Long id);

    Page<SearchUserDto> searchUserProfile(String keyword, Pageable pageable);
}
