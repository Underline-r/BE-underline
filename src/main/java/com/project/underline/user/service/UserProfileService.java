package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.FollowUserInfoDto;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileDto getUserProfile(Long profileUserId) {
        existUser(profileUserId);
        ProfileSearchCondition profileSearchCondition = new ProfileSearchCondition(profileUserId, SecurityUtil.getCurrentUserId());

        UserProfileDto profileDto = userRepository
                .getUserProfile(profileSearchCondition);

        if (profileUserId.equals(SecurityUtil.getCurrentUserId())) {
            profileDto.setMyPage(true);
        }

        return profileDto;
    }

    public List<FollowUserInfoDto> getFollowingList(Long profileUserId) {
        existUser(profileUserId);
        List<FollowUserInfoDto> followingList = userRepository.getFollowingList(profileUserId);
        return followingList;
    }

    public List<FollowUserInfoDto> getFollowerList(Long profileUserId) {
        existUser(profileUserId);
        List<FollowUserInfoDto> followerList = userRepository.getFollowerList(profileUserId);
        return followerList;
    }

    @Transactional
    public void changeUserProfile(Long currentUserId, UserProfileDto dto) {
        User findUser = existUser(currentUserId);
        findUser.changeProfile(dto.getNickname(), dto.getDescription());

        userRepository.save(findUser);
    }

    private User existUser(Long profileUserId) {
        User findUser = userRepository.findById(profileUserId).orElseThrow(
                () -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)
        );

        return findUser;
    }
}