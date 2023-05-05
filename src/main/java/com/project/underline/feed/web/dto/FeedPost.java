package com.project.underline.feed.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
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
    private long pickCount;
    private long commentCount;

    private Boolean isPicked;
    private Boolean isBookmarked;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    // private ArrayList<String> hashtags;

    @QueryProjection
    public FeedPost(long postId,String userNickname,String userProfileImage, long userId, String content, long pickCount, long commentCount,String source,LocalDateTime createdDate){
        this.postId = postId;
        this.userNickname = userNickname;
        this.userProfileImage = userProfileImage;
        this.userId = userId;
        this.content = content;
        this.pickCount = pickCount;
        this.commentCount = commentCount;
        this.source = source;
        this.isBookmarked = false;
        this.isPicked = false;
        this.createdDate = createdDate;
    }

    public void setIsPicked(){
        this.isPicked = true;
    }

    public void setIsBookmarked(){
        this.isBookmarked = true;
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
