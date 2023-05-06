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
    private Boolean isInitialLogin;
    private String grantType;
    private String accessToken;
    private String refreshToken;

    public boolean getIsInitialLogin() {
        return isInitialLogin;
    }

    public void setInitialLogin(Boolean initialLogin) {
        isInitialLogin = initialLogin;
    }
}
