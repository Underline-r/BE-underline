package com.project.underline.bookmark.web.dto;

import com.project.underline.bookmark.entity.Bookmark;
import com.project.underline.post.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookmarkResponse {

    private List<EachBookmarkedPost> bookmarks;

    public BookmarkResponse(List<Bookmark> bookmarkedPosts){
        this.bookmarks = new ArrayList<EachBookmarkedPost>();

        for(Bookmark eachPost : bookmarkedPosts){
            this.bookmarks.add(new EachBookmarkedPost(eachPost.getPost()));
        }
    }

    @Getter
    public static class EachBookmarkedPost{
        private Long postId;
        private String userNickname;
        private String content;
        private String sourceTitle;

        public EachBookmarkedPost(Post post){
            this.postId = post.getPostId();
            this.content = post.getContent();
            this.userNickname = post.getUser().getNickname();
            if(post.getSource() != null){
                this.sourceTitle = post.getSource().getTitle();
            }
        }
    }
}
