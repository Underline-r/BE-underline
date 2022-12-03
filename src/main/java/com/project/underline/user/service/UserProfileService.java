package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.FollowRelationRepository;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final FollowRelationRepository followRelationRepository;

    @Transactional
    public UserProfileDto getUserProfile(Long profileUserId) {

        User user = userRepository.findById(profileUserId).orElseThrow(
                () -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)
        );

        // TODO : 여러번 타지 않고 한방 쿼리로 가져올 수 있는 방법 있을까?
        UserProfileDto profileDto = new UserProfileDto().EntityToDto(user);
        profileDto.setFollowerCount(followRelationRepository.countByToUserId(profileUserId));
        profileDto.setFollowingCount(followRelationRepository.countByFromUserId(profileUserId));
        profileDto.setSubscribeState(followRelationRepository.existsByToUserIdAndFromUserId(profileUserId, SecurityUtil.getCurrentUserId()));

        return profileDto;
    }
}