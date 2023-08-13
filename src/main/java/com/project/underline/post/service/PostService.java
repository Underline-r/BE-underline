package com.project.underline.post.service;

import com.project.underline.bookmark.entity.repository.BookmarkRepository;
import com.project.underline.category.entity.PostCategoryRelation;
import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Comment;
import com.project.underline.post.entity.Hashtag;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.PostExternalShareAttempts;
import com.project.underline.post.entity.repository.CommentRepository;
import com.project.underline.post.entity.repository.PickRepository;
import com.project.underline.post.entity.repository.PostExternalShareAttemptsRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PostDetailResponse;
import com.project.underline.post.web.dto.PostRequest;
import com.project.underline.post.web.dto.ShareRequest;
import com.project.underline.post.web.dto.UserCreatedPostListResponse;
import com.project.underline.source.service.SourceService;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.FollowRelationRepository;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PickRepository pickRepository;
    private final BookmarkRepository bookmarkRepository;
    private final FollowRelationRepository followRelationRepository;
    private final PostExternalShareAttemptsRepository postExternalShareAttemptsRepository;
    private final CommentRepository commentRepository;
    private final SourceService sourceService;
    private final PostViewService postViewService;

    @Transactional
    public void registerPost(PostRequest postRequest) {
        try {
            if (postRequest.getSource() != null) {
                Post registerNewPost = new Post(userRepository.findById(SecurityUtil.getCurrentUserId()).get(), postRequest.getContent(), sourceService.checkExistSource(postRequest.getSource()));
                registerNewPost = setHashtagsAndCategory(registerNewPost, postRequest);
                postRepository.save(registerNewPost);
            } else {
                Post registerNewPost = new Post(userRepository.findById(SecurityUtil.getCurrentUserId()).get(), postRequest.getContent());

                if (postRequest.getCategory() != null) {
                    registerNewPost = setHashtagsAndCategory(registerNewPost, postRequest);
                }

                postRepository.save(registerNewPost);
            }
        }catch (RuntimeException e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PostDetailResponse inquiryPost (Long postId){
        try {
            PostDetailResponse detailResponse = new PostDetailResponse(postRepository.findByPostId(postId).get(), postViewService.getViewCount(postId));
            setUserStatus(detailResponse);
//            setPostCountOption(detailResponse);
            return detailResponse;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PostDetailResponse setUserStatus (PostDetailResponse postDetailResponse){
        boolean isFollwed = followRelationRepository.existsByToUserIdAndFromUserId(postDetailResponse.getUserId(), SecurityUtil.getCurrentUserId());
        boolean isBookmarked = bookmarkRepository.existsByPost_PostIdAndUser_Id(postDetailResponse.getPostId(), SecurityUtil.getCurrentUserId());
        boolean isPicked = pickRepository.existsByPost_PostIdAndUser_Id(postDetailResponse.getPostId(), SecurityUtil.getCurrentUserId());

        postDetailResponse.setUserCheck(isPicked, isBookmarked, isFollwed);

        return postDetailResponse;
    }

    @Transactional(readOnly = true)
    public PostDetailResponse setPostCountOption (PostDetailResponse postDetailResponse){
        Long pickCount = pickRepository.countAllByPost_PostId(postDetailResponse.getPostId());
        Optional<List<Comment>> comments = commentRepository.findAllByPost_PostId(postDetailResponse.getPostId());
        if (comments.isPresent() && comments.get().size() > 0) {
            postDetailResponse.setCountOption(pickCount, (long) comments.get().size(), comments.get().get(0).getContent());
        } else {
            postDetailResponse.setCountOption(pickCount, 0L, null);
        }
        return postDetailResponse;
    }


    @Transactional
    public void patchPost (Long postId, PostRequest postRequest){
        try {
            Post updatePost = postRepository.findByPostId(postId).get();
            SecurityUtil.checkSameUser(updatePost.getUser().getId());

            updatePost.update(postRequest.getContent());
            updatePost = setHashtagsAndCategory(updatePost, postRequest);

            postRepository.save(updatePost);

        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Transactional
    public void deletePost (Long postId){
        try {
            Post deletePost = postRepository.findByPostId(postId).get();

            SecurityUtil.checkSameUser(deletePost.getUser().getId());

            postRepository.delete(deletePost);

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public UserCreatedPostListResponse inquiryUserCreatedPost (String userNickname){
        List<Post> userCreatedPost = postRepository.findAllByUserId(returnUserId(userNickname));
        UserCreatedPostListResponse userCreatedPostList = new UserCreatedPostListResponse(userCreatedPost);

        return userCreatedPostList;
    }

    private Post setHashtagsAndCategory (Post registerPost, PostRequest postRequest){

        List<Hashtag> hashtags = new ArrayList<Hashtag>();
        List<PostCategoryRelation> postCategoryRelations = new ArrayList<PostCategoryRelation>();

        registerPost.removeAllHashtagsAndCategory();

        if (postRequest.getHashtag() != null) {
            List<String> requestHashtags = postRequest.getHashtag();
            for (String eachHashtag : requestHashtags) {
                hashtags.add(new Hashtag(registerPost, eachHashtag));
            }
        }

        if (postRequest.getCategory() != null) {
            List<String> requestCategory = postRequest.getCategory();
            for (String eachCategory : requestCategory) {
                postCategoryRelations.add(new PostCategoryRelation(registerPost, eachCategory));
            }
        }

        registerPost.setHashtagsAndCategory(hashtags, postCategoryRelations);

        return registerPost;
    }

    private Long returnUserId (String userNickname){
        Optional<User> findUser = Optional.ofNullable(userRepository.findByNickname(userNickname));
        return findUser.get().getId();
    }

    public void externalSharingCount (ShareRequest shareRequest){
        try {
            Optional<PostExternalShareAttempts> findByPost_PostId = postExternalShareAttemptsRepository.findByPost_PostIdAndShareTarget(shareRequest.getPostId(), shareRequest.getShareTarget());
            if (findByPost_PostId.isPresent()) {
                findByPost_PostId.get().update();
                postExternalShareAttemptsRepository.save(findByPost_PostId.get());
            } else {
                postExternalShareAttemptsRepository.save(new PostExternalShareAttempts(shareRequest.getPostId(), shareRequest.getShareTarget(), 1L));
            }
        } catch (DataAccessException e) {
            throw new UnderlineException(ErrorCode.WRONG_APPROACH);
        }
    }
}
