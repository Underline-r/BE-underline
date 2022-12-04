package com.project.underline.post.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Pick;
import com.project.underline.post.entity.repository.PickRepository;
import com.project.underline.post.web.dto.PickRequest;
import com.project.underline.post.web.dto.PickedUserListResponse;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PickService {
    private final PickRepository pickRepository;
    private final UserRepository userRepository;
    public void checkUserPostPick(PickRequest pickRequest) {
        // 해당 게시글을 좋아요했는지 확인
        Optional<Pick> checkAlreadyPicked = pickRepository.findByPostIdAndUserId(pickRequest.getPostId(), SecurityUtil.getCurrentUserId());

        // 존재한다면 삭제
        if(checkAlreadyPicked.isPresent()){
            pickRepository.delete(checkAlreadyPicked.get());
            return;
        }

        pickRepository.save(
                Pick.builder()
                        .postId(pickRequest.getPostId())
                        .userId(SecurityUtil.getCurrentUserId())
                        .build()
        );
    }

    public PickedUserListResponse inquiryPickedUser(Long postId) {
        PickedUserListResponse pickedUserListResponse = new PickedUserListResponse();

        List<Pick> pickedList = pickRepository.findAllByPostId(postId);
        List<User> pickedUserList = new ArrayList<User>();

        for (Pick eachPick: pickedList) {
            pickedUserList.add(userRepository.findById(eachPick.getUserId()).get());
        }

        pickedUserListResponse.pickUserListUp(pickedUserList);

        return pickedUserListResponse;
    }
}
