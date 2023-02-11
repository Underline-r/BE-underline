package com.project.underline.feed.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class FeedPost {
    // 그냥 post말고 feed에서 보여지기 위한 post
    private long postId;
    private String userNickname;
    private String content;
    private long likeCount;
    private long commentCount;
    private ArrayList<String> hashtags;

    @QueryProjection
    public FeedPost(long postId,String userNickname, String content, long likeCount, long commentCount, String hashtags){
        this.postId = postId;
        this.userNickname = userNickname;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.hashtags = stringToArr(hashtags);
    }

    private ArrayList<String> stringToArr(String target){
        String[] ArraysStr = target.split(" ");
        ArrayList<String> result = new ArrayList<String>();

        if(target == null){
            return null;
        }

        for (String str: ArraysStr) {
            result.add(str);
        }

        return result;
    }
}
