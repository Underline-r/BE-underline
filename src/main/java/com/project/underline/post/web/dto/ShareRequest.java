package com.project.underline.post.web.dto;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.metadata.StringUtils;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ShareRequest {
    @NotBlank
    private String shareTarget;

    @NotNull
    @Min(1)
    private Long postId;

    @AssertTrue(message = ResponseMessage.INVALID_SHARE_TARGET)
    private boolean isShareTargetValid() {
        return StringUtils.TWITTER.equals(shareTarget)
                || StringUtils.KAKAOTAlK.equals(shareTarget)
                || StringUtils.FACEBOOK.equals(shareTarget);
    }
}

