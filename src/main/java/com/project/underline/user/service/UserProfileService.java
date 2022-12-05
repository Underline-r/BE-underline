package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.UserDto;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    @Transactional
    public UserProfileDto getUserProfile(Long profileUserId) {

        User user = userRepository.findById(profileUserId).orElseThrow(
                () -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)
        );

        ProfileSearchCondition profileSearchCondition = new ProfileSearchCondition();

        profileSearchCondition.setProfileUserId(profileUserId);
        profileSearchCondition.setLoginUserId(SecurityUtil.getCurrentUserId());

        UserProfileDto profileDto = userRepository
                .getUserProfile(profileSearchCondition);

        return profileDto;
    }

    public List<UserDto> getFollowingList(Long id) {
        List<UserDto> followingList = userRepository.getFollowingList(id);
        return followingList;
    }

    public List<UserDto> getFollowerList(Long id) {
        List<UserDto> followerList = userRepository.getFollowerList(id);
        return followerList;
    }
}