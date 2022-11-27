package com.project.underline.post.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Pick;
import com.project.underline.post.entity.repository.PickRepository;
import com.project.underline.post.web.dto.PickRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PickService {
    private final PickRepository pickRepository;
    public void checkUserPostPick(PickRequest pickRequest) {
        // 해당 게시글을 좋아요했는지 확인
        Optional<Pick> checkAlreadyLiked = pickRepository.findByPostIdAndUserId(pickRequest.getPostId(), SecurityUtil.getCurrentUserId());

        // 존재한다면 삭제
        if(checkAlreadyLiked.isPresent()){
            pickRepository.delete(checkAlreadyLiked.get());
            return;
        }

        pickRepository.save(
                Pick.builder()
                        .postId(pickRequest.getPostId())
                        .userId(SecurityUtil.getCurrentUserId())
                        .build()
        );
    }
}
