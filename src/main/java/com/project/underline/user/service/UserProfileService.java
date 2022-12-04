package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}