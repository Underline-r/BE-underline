package com.project.underline.post.web.dto;

import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.metadata.ResponseMessage;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostRequest {

    @NotNull(message = ResponseMessage.INVALID_CONTENT)
    private List<String> category;

    @NotNull(message = ResponseMessage.INVALID_CONTENT)
    private String reference;

    @NotNull(message = ResponseMessage.INVALID_CONTENT)
    private String content;

    private List<String> hashtag;

}
