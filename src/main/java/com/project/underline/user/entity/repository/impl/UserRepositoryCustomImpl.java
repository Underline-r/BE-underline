package com.project.underline.user.entity.repository.impl;

import com.project.underline.user.entity.repository.UserRepositoryCustom;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.QUserDto;
import com.project.underline.user.web.dto.QUserProfileDto;
import com.project.underline.user.web.dto.UserDto;
import com.project.underline.user.web.dto.UserProfileDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.underline.user.entity.QUser.user;
import static com.project.underline.user.entity.QUserFollowRelation.userFollowRelation;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserProfileDto getUserProfile(ProfileSearchCondition condition) {

        return queryFactory
                .select(new QUserProfileDto(
                                user.email,
                                user.nickname,
                                queryFactory
                                        .selectFrom(userFollowRelation)
                                        .where(toUserIdEq(condition.getProfileUserId())
                                                .and(fromUserIdEq(condition.getLoginUserId()))).exists(),
                                user.toUserFollowRelations.size(),
                                user.fromUserFollowRelations.size())
                )
                .from(user)
                .where(userIdEq(condition.getProfileUserId()))
                .leftJoin(user.toUserFollowRelations, userFollowRelation)
                .leftJoin(user.fromUserFollowRelations, userFollowRelation)
                .groupBy(user.email, user.nickname)
                .fetchOne();
    }

    @Override
    public List<UserDto> getFollowingList(Long id) {
        return queryFactory
                .select(
                        new QUserDto(
                                userFollowRelation.toUser.email,
                                userFollowRelation.toUser.nickname)
                        )
                .from(userFollowRelation)
                .where(fromUserIdEq(id))
                .fetch();
    }

    @Override
    public List<UserDto> getFollowerList(Long id) {
        return queryFactory
                .select(
                        new QUserDto(
                                userFollowRelation.fromUser.email,
                                userFollowRelation.fromUser.nickname)
                )
                .from(userFollowRelation)
                .where(toUserIdEq(id))
                .fetch();
    }

    private BooleanExpression toUserIdEq(Long toUserId) {
        return toUserId != null ? userFollowRelation.toUser.id.eq(toUserId) : null;
    }
    private BooleanExpression fromUserIdEq(Long fromUserId) {
        return fromUserId != null ? userFollowRelation.fromUser.id.eq(fromUserId) : null;
    }
    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }
}
