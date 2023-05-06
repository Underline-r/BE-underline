package com.project.underline.post.web.dto;

import com.project.underline.common.metadata.ResponseMessage;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class PostRequest {

    private List<String> category;

    private String sources;

    @NotEmpty(message = ResponseMessage.INVALID_CONTENT)
    private String content;

    private List<String> hashtag;

}
