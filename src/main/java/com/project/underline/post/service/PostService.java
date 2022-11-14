package com.project.underline.post.service;

import com.project.underline.common.exception.customexception.InvalidTokenException;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PostDetailResponse;
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
        try{

            // TO-DO. post 등록 -> hashtag 등록을 한개의 메소드(그리고 트랜잭션)내에서 해결했는데

            Post registerNewPost = Post.builder()
                    .userId(SecurityUtil.getCurrentUserId())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .build();

            postRepository.save(registerNewPost);

            List<Hashtag> hashtags = new ArrayList<Hashtag>();
            for(String eachHashtag : postRequest.getHashtag()){
                hashtags.add(new Hashtag(registerNewPost.getPostId(),eachHashtag));
            }

            hashtagRepository.saveAll(hashtags);

        }catch (RuntimeException e){
            throw e;
        }
    }

    public PostDetailResponse inquiryPost(Long postId) {
        try{
            Post findPost = postRepository.findByPostId(postId);
            return new PostDetailResponse(findPost);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional
    public void patchPost(Long postId, PostRequest postRequest) {
        try{
            Post updatePost = postRepository.findByPostId(postId);

            // TO-DO. 리소스를 수정하려는 유저와 기존 리소스의 주인인 유저가 같은지 검사해주는 로직을 공통으로 뺄수있을까요? -> 리팩토링 완.
            SecurityUtil.checkSameUser(updatePost.getUserId());

            updatePost.update(postRequest.getTitle(), postRequest.getContent());
            postRepository.save(updatePost);
        }catch (RuntimeException e){
            throw e;
        }
    }

}
