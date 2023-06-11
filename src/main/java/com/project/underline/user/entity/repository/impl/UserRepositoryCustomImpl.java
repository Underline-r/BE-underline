package com.project.underline.user.entity.repository.impl;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.search.web.dto.QSearchUserDto;
import com.project.underline.search.web.dto.SearchUserDto;
import com.project.underline.user.entity.repository.UserRepositoryCustom;
import com.project.underline.user.entity.repository.dto.ProfileSearchCondition;
import com.project.underline.user.web.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.underline.category.entity.QUserCategoryRelation.userCategoryRelation;
import static com.project.underline.post.entity.QHashtag.hashtag;
import static com.project.underline.post.entity.QPost.post;
import static com.project.underline.source.entity.QSource.source;
import static com.project.underline.user.entity.QUser.user;
import static com.project.underline.user.entity.QUserFollowRelation.userFollowRelation;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserProfileDto selectUserProfile(ProfileSearchCondition condition) {

        return queryFactory
                .select(new QUserProfileDto(
                                user.email,
                                user.nickname,
                                user.description,
                                user.imagePath,
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
    public List<FollowUserInfoDto> selectFollowingList(Long id) {
        return queryFactory
                .select(
                        new QFollowUserInfoDto(
                                userFollowRelation.toUser.email,
                                userFollowRelation.toUser.nickname)
                        )
                .from(userFollowRelation)
                .where(fromUserIdEq(id))
                .fetch();
    }

    @Override
    public List<FollowUserInfoDto> selectFollowerList(Long id) {
        return queryFactory
                .select(
                        new QFollowUserInfoDto(
                                userFollowRelation.fromUser.email,
                                userFollowRelation.fromUser.nickname)
                )
                .from(userFollowRelation)
                .where(toUserIdEq(id))
                .fetch();
    }

    @Override
    public List<UserPostDto> selectUserPostList(Long id) {

        return queryFactory
                .select(
                        new QUserPostDto(
                                post.content,
                                post.modifiedDate
                        )
                )
                .from(post)
                .join(post.user, user)
                .where(userIdEq(id))
                .fetch();
    }

    @Override
    public List<String> selectUserHashtagList(Long id) {
        return queryFactory
                .select(
                        hashtag.hashtagName
                )
                .from(hashtag)
                .join(hashtag.post, post)
                .join(post.user, user)
                .where(userIdEq(id))
                .fetch();
    }

    @Override
    public List<String> selectUserCategoryList(Long id) {
        return queryFactory
                .select(
                        userCategoryRelation.category
                )
                .from(userCategoryRelation)
                .join(userCategoryRelation.user, user)
                .where(userIdEq(id))
                .fetch();
    }

    /**
     * TODO : from 절 subquery가 안되기 때문에, 최적화 위해
     * @Subselct 사용한 Entity 이용 모수 줄이는 방법 있음
     */
    @Override
    public List<UserSourceDto> selectUserSourceList(Long id) {
        return queryFactory
                .select(
                        new QUserSourceDto(
                                source.title
                        )
                )
                .from(source)
                .groupBy(source.title)
                .join(source.postList, post)
                .join(post.user, user)
                .on(userIdEq(id))
                .fetch();
    }

    @Override
    public Page<SearchUserDto> searchUserProfile(String keyword, Pageable pageable) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        List<SearchUserDto> contents = queryFactory
                .select(
                        new QSearchUserDto(
                                user.id,
                                user.imagePath,
                                user.nickname,
                                userFollowRelation.id.isNotNull()
                        )
                )
                .from(user)
                .where(user.nickname.contains(keyword)
                )
                .leftJoin(user.toUserFollowRelations, userFollowRelation)
                .on(userFollowRelation.fromUser.id.eq(currentUserId))
                .orderBy(user.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(user.count())
                .from(user)
                .where(user.nickname.contains(keyword))
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(contents, pageable, total);
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
