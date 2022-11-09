package com.project.underline.common.util;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static java.lang.Long.parseLong;

/**
 * @AuthenticationPrincipal 대신 request를 보내는 사용자의 정보를 꺼낼 수 있게 만든 Util성 객체
 */
@NoArgsConstructor
public class SecurityUtil {
    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authentication information.");
        }
        return parseLong(authentication.getName());
    }
}
