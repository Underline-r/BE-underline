package com.project.underline.feed.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FeedResponse {
    List<FeedPost> feed;

    boolean isLast;

    public FeedResponse(){
    }

    public boolean getIsLast(){
        // 실제로 쓰진 않지만 Lombok이 isLast를 last라고 출력하지 않도록 생성해줌
        return isLast;
    }

    public FeedResponse setPickYn(List<Long> checkPostList){
        for (FeedPost post : feed) {
            if (checkPostList.contains(post.getPostId())) {
                post.setPickYn();
            }
        }
        return this;
    }

    public FeedResponse setBookmarkYn(List<Long> checkPostList){
        for (FeedPost post : feed) {
            if (checkPostList.contains(post.getPostId())) {
                post.setBookmarkYn();
            }
        }
        return this;
    }
    public void setFeedPosts(List<FeedPost> feedPosts){
        this.feed = feedPosts;
    }

    public void isLastCheck(){
        if(feed.size() < 10){
            this.isLast = true;
        }else{
            this.isLast =  false;
        }
    }
}
