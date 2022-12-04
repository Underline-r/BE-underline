package com.project.underline.user.entity.repository.dto;

import lombok.Data;

@Data
public class ProfileSearchCondition {
    private Long profileUserId;
    private Long loginUserId;
}
