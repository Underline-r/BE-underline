package com.project.underline.user.service;

import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.metadata.ErrorCode;
import com.project.underline.user.entity.User;
import com.project.underline.user.entity.UserFollowRelation;
import com.project.underline.user.entity.repository.FollowRelationRepository;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FollowRelationService {

    private final FollowRelationRepository followRelationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(Long toUserId, Long fromUserId) {
        ArrayList<User> users = checkUserExist(toUserId, fromUserId);
        UserFollowRelation userFollowRelation = new UserFollowRelation(users.get(0), users.get(1));
        followRelationRepository.save(userFollowRelation);

    }

    @Transactional
    public void unfollow(Long toUserId, Long fromUserId) {
        checkUserExist(toUserId, fromUserId);
        followRelationRepository.deleteByToUserIdAndFromUserId(toUserId, fromUserId);
    }

    /**
     * UserFollowRelation 생성자 param 사용 위해 toUser, fromUser return
     */
    private ArrayList<User> checkUserExist(Long toUserId, Long fromUserId) {
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER));
        User fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new UnderlineException(ErrorCode.CANNOT_FOUND_USER));

        if (toUser.equals(fromUser)) {
            throw new UnderlineException(ErrorCode.CANNOT_FOLLOW_MYSELF);
        }

        ArrayList<User> users = new ArrayList<>();
        users.add(toUser);
        users.add(fromUser);
        return users;
    }
}
