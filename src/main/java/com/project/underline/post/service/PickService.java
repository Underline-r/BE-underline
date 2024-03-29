package com.project.underline.post.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Pick;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.PickRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.PickRequest;
import com.project.underline.post.web.dto.PickedUserListResponse;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PickService {
    private final PickRepository pickRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void checkUserPostPick(PickRequest pickRequest) {
        // 해당 게시글을 좋아요했는지 확인
        Optional<Pick> checkAlreadyPicked = pickRepository.findByPostAndUser(new Post(pickRequest.getPostId()), new User(SecurityUtil.getCurrentUserId()));

        // 존재한다면 삭제
        if(checkAlreadyPicked.isPresent()){
            pickRepository.delete(checkAlreadyPicked.get());
            return;
        }

        pickRepository.save(
                Pick.builder()
                        .post(postRepository.findById(pickRequest.getPostId())
                                .orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_POST)))
                        .user(userRepository.findById(SecurityUtil.getCurrentUserId())
                                .orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER)))
                        .build()
        );
    }

    public PickedUserListResponse inquiryPickedUser(Long postId) {
        PickedUserListResponse pickedUserListResponse = new PickedUserListResponse();

        List<Pick> pickedList = pickRepository.findAllByPost(new Post(postId));
        pickedUserListResponse.pickUserListUp(pickedList);

        return pickedUserListResponse;
    }
}
