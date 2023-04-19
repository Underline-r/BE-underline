package com.project.underline.post.web.dto;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.metadata.StringUtils;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ShareRequest {
    @NotBlank
    private String shareTarget;

    @NotBlank
    private Long postId;

    public ShareRequest(String shareTarget) {
        if (!StringUtils.TWITTER.equals(shareTarget) || !StringUtils.KAKAOTAlK.equals(shareTarget) || !StringUtils.FACEBOOK.equals(shareTarget)) {
            throw new UnderlineException(ErrorCode.INVALID_SHARE_TARGET);
        }
        this.shareTarget = shareTarget;
    }
}

