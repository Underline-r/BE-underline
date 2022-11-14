package com.project.underline.post.web.dto;


import com.project.underline.post.entity.ContentType;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequest {

    private String title;
    private String content;
    private String contentType;
    private List<String> hashtag;

    public ContentType contentSize(){
        if(content.length() > 140){
            return ContentType.LONG;
        }
        return ContentType.SHORT;
    }

}
