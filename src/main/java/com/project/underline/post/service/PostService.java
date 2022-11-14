package com.project.underline.post.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.ContentType;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public void registerPost(PostRequest postRequest) {

        // TO-DO. post 등록 -> hashtag 등록을 한개의 메소드(그리고 트랜잭션)내에서 해결했는데

        Post registerNewPost = Post.builder()
                .userId(SecurityUtil.getCurrentUserId())
                .title(postRequest.getTitle())
                .contentType(postRequest.contentSize())
                .build();

        postRepository.save(registerNewPost);

        List<Hashtag> hashtags = new ArrayList<Hashtag>();
        for(String eachHashtag : postRequest.getHashtag()){
            hashtags.add(new Hashtag(registerNewPost.getPostId(),eachHashtag));
        }

        hashtagRepository.saveAll(hashtags);
    }
}
