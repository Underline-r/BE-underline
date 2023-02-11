package com.project.underline.feed.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WebFeedResponse {
    List<FeedPost> WebFeed;

    public WebFeedResponse(List<FeedPost> feedPosts){
        this.WebFeed = feedPosts;
    }
}
