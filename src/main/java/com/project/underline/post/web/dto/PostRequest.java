package com.project.underline.post.web.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class PostRequest {

    private String title;
    private String content;
    private List<String> hashtag;
    private List<String> category;
    private String references;

}
