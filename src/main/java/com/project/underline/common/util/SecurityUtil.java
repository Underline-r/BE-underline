package com.project.underline.common.util;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
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
            throw new UnderlineException(ErrorCode.MISMATCH_TOKEN);
        }
        return parseLong(authentication.getName());
    }

    public static void checkSameUser(Long userId){
        Long currentUserId = getCurrentUserId();
        if(!userId.equals(currentUserId)) {
            throw new UnderlineException(ErrorCode.INVALID_TOKEN);
        }
    }
}
