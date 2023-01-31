package com.project.underline.feed.web.dto;

import java.util.ArrayList;

public class FeedPost {
    // 그냥 post말고 feed에서 보여지기 위한 post
    private String author;
    private String content;
    private long likeCount;
    private long commentCount;
    private ArrayList<String> hashtags;


}
