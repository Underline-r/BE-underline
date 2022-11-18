package com.project.underline.post.web.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class PostRequest {

    private String title;
    private String content;
    private String contentType;
    private List<String> hashtag;

}
