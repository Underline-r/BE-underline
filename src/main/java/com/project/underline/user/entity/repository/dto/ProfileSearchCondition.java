package com.project.underline.user.entity.repository.dto;

import lombok.Data;

@Data
public class ProfileSearchCondition {
    private Long profileUserId;
    private Long loginUserId;

    public ProfileSearchCondition(Long profileUserId, Long currentUserId) {
        this.profileUserId = profileUserId;
        this.loginUserId = currentUserId;
    }
}
