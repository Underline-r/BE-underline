package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.QUserProfileDto;
import com.project.underline.user.web.dto.UserProfileDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.project.underline.user.entity.QUser.user;
import static com.project.underline.user.entity.QUserFollowRelation.userFollowRelation;

@Repository
public class UserQueryRepository {
    /**
     * 복잡할 때는 기존 인터페이스 다중 상속 하지 않고 이 방식대로
     * QueryRepository 직접 구현
     */

    private final JPAQueryFactory queryFactory;

    public UserQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public UserProfileDto getUserProfile(ProfileSearchCondition condition) {

        return queryFactory
                .select(new QUserProfileDto(
                        user.email,
                        user.nickname,
                        user.description,
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
