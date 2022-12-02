package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.UserFollowRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRelationRepository extends JpaRepository<UserFollowRelation, Long> {

    @Modifying
    @Query("delete from UserFollowRelation where toUserId = :toUserId and fromUser.id = :fromUserId")
    void deleteByToUserIdAndFromUserId(@Param("toUserId") Long toUserId, @Param("fromUserId") Long fromUserId);
}
