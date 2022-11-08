package com.project.underline.common.jwt.refreshtoken;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class RefreshToken {
    @Id
    private String userEmail;

    private String refreshValue;

    public RefreshToken updateValue(String token) {
        this.refreshValue = token;
        return this;
    }

    @Builder
    public RefreshToken(String userEmail, String refreshValue) {
        this.userEmail = userEmail;
        this.refreshValue = refreshValue;
    }
}
