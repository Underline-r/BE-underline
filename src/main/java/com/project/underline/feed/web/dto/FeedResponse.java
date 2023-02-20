package com.project.underline.feed.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FeedResponse {
    List<FeedPost> feed;

    Boolean isLast;

    public FeedResponse(){
    }

    public FeedResponse setPickYn(List<Long> checkPostList){
        for (FeedPost post : feed) {
            if (checkPostList.contains(post.getPostId())) {
                post.setIsPicked();
            }
        }
        return this;
    }

    public FeedResponse setBookmarkYn(List<Long> checkPostList){
        for (FeedPost post : feed) {
            if (checkPostList.contains(post.getPostId())) {
                post.setIsBookmarked();
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
