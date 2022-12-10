package com.project.underline.post.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PostDetailResponse;
import com.project.underline.post.web.dto.PostRequest;
import com.project.underline.post.web.dto.UserCreatedPostListResponse;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    private final UserRepository userRepository;

    @Transactional
    public void registerPost(PostRequest postRequest) {
        try{

            // TO-DO. post 등록 -> hashtag 등록을 한개의 메소드(그리고 트랜잭션)내에서 해결했는데

            Post registerNewPost = Post.builder()
                    .user(userRepository.findById(SecurityUtil.getCurrentUserId())
                            .orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)))
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .categoryCode(postRequest.getCategoryCode())
                    .build();

            postRepository.save(registerNewPost);

            if(postRequest.getHashtag().size() > 0){
                List<Hashtag> hashtags = new ArrayList<Hashtag>();
                for(String eachHashtag : postRequest.getHashtag()){
                    hashtags.add(new Hashtag(registerNewPost,eachHashtag));
                }

                hashtagRepository.saveAll(hashtags);
            }


        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
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
            SecurityUtil.checkSameUser(updatePost.getUser().getId());

            updatePost.update(postRequest.getTitle(), postRequest.getContent(),postRequest.getCategoryCode());
            postRepository.save(updatePost);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional
    public void deletePost(Long postId) {
        try{
            Post deletePost = postRepository.findByPostId(postId);

            SecurityUtil.checkSameUser(deletePost.getUser().getId());

            postRepository.delete(deletePost);

        }catch (RuntimeException e){
            throw e;
        }
    }

    public UserCreatedPostListResponse inquiryUserCreatedPost(String userNickname) {
        List<Post> userCreatedPost = postRepository.findAllByUserId(returnUserId(userNickname));
        UserCreatedPostListResponse userCreatedPostList = new UserCreatedPostListResponse(userCreatedPost);

        return userCreatedPostList;
    }

    private Long returnUserId(String userNickname){
        Optional<User> findUser = Optional.ofNullable(userRepository.findByNickname(userNickname));
        return findUser.get().getId();
    }
}
