package com.project.underline.feed.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class FeedPost {
    // 그냥 post말고 feed에서 보여지기 위한 post
    private long postId;
    private String userNickname;

    private long userId;

    private String userProfileImage;

    private String content;

    private String source;
    private long likeCount;
    private long commentCount;

    private boolean isPicked;
    private boolean isBookmarked;

    // private ArrayList<String> hashtags;

    @QueryProjection
    public FeedPost(long postId,String userNickname,String userProfileImage, long userId, String content, long likeCount, long commentCount,String source){
        this.postId = postId;
        this.userNickname = userNickname;
        this.userProfileImage = userProfileImage;
        this.userId = userId;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.source = source;
        this.isBookmarked = false;
        this.isPicked = false;
    }

    public FeedPost setPickYn(){
        this.isPicked = true;
        return this;
    }

    public FeedPost setBookmarkYn(){
        this.isBookmarked = true;
        return this;
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
