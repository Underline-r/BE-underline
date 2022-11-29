package com.project.underline.user.service;

import com.project.underline.user.entity.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowRelationService {

    private final FollowRelationRepository followRelationRepository;

//    private final UserRepository userRepository;

    @Transactional
    public void follow(Long toUserId, Long fromUserId) {

//        User user = userRepository.findById(fromUserId).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
//        UserFollowRelation userFollowRelation = new UserFollowRelation(toUserId, user);
//        followRelationRepository.save(userFollowRelation);
        followRelationRepository.saveFollowRelation(toUserId, fromUserId);

    }

    @Transactional
    public void unfollow(Long toUserId, Long fromUserId) {
        followRelationRepository.deleteByToUserIdAndFromUserId(toUserId, fromUserId);
    }
}
