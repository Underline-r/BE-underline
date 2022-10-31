package com.project.underline.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
