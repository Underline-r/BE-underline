package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Post;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserCreatedPostListResponse {
    // 특정 유저가 작성한 게시글 리스트
    List<PostDetailResponse> userCreatedPostList;

    public UserCreatedPostListResponse(List<Post> userPosts){
        this.userCreatedPostList = userPosts
                .stream().map(t->new PostDetailResponse(t)).collect(Collectors.toList());
    }
}
