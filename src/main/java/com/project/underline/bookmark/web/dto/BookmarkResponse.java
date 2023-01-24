package com.project.underline.bookmark.web.dto;

import com.project.underline.post.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookmarkResponse {

    private List<EachBookmarkedPost> bookmarkedPosts;

    public BookmarkResponse(List<Post> bookmarkedPosts){
        this.bookmarkedPosts = new ArrayList<EachBookmarkedPost>();

        for(Post eachPost : bookmarkedPosts){
            this.bookmarkedPosts.add(new EachBookmarkedPost(eachPost));
        }
    }

    @Getter
    public static class EachBookmarkedPost{
        private Long postId;
        private String title;
        private String content;
        private String userNickname;
        private String referenceTitle;

        public EachBookmarkedPost(Post post){
            this.postId = post.getPostId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.userNickname = post.getUser().getNickname();
            this.referenceTitle = post.getReference().getTitle();

        }
    }
}
