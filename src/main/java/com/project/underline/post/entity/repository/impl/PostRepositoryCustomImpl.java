package com.project.underline.post.entity.repository.impl;

import com.project.underline.post.entity.repository.PostRepositoryCustom;
import com.project.underline.search.web.dto.*;
import com.project.underline.user.entity.User;
import com.project.underline.user.web.dto.QUserPostDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.underline.post.entity.QHashtag.hashtag;
import static com.project.underline.post.entity.QPick.pick;
import static com.project.underline.post.entity.QPost.post;
import static com.project.underline.source.entity.QSource.source;
import static com.project.underline.user.entity.QUser.user;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserPostDto> findAllByMyPick(User findUser) {
        return queryFactory
                .select(
                        new QUserPostDto(
                                post.postId,
                                post.content,
                                post.modifiedDate
                        )
                )
                .from(pick)
                .join(pick.post, post)
                .join(pick.user, user)
                .on(userIdEq(findUser.getId()))
                .orderBy(post.modifiedDate.desc())
                .fetch();
    }

    @Override
    public Page<SearchPostDto> searchPostList(String keyword, Pageable pageable) {
        List<SearchPostDto> contents = queryFactory
                .select(
                        new QSearchPostDto(
                                post.postId,
                                post.content,
                                source.title,
                                user.nickname
                        )
                )
                .from(post)
                .leftJoin(post.source, source)
                .leftJoin(post.user, user)
                .where(post.content.contains(keyword))
                .orderBy(post.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(post.count())
                .from(post)
                .where(post.content.contains(keyword)).fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<SearchSourceDto> searchSourceList(String keyword, Pageable pageable) {

        List<SearchSourceDto> contents = queryFactory
                .select(
                        new QSearchSourceDto(
                                post.count(),
                                source.title
                        )
                )
                .from(source)
                .leftJoin(source.postList, post)
                .leftJoin(post.user, user)
                .where(source.title.contains(keyword))
                .groupBy(source.title)
                .orderBy(source.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(
                        source.count()
                )
                .from(source)
                .groupBy(source.title)
                .having(source.title.contains(keyword))
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Page<SearchHashtagDto> searchHashtagList(String keyword, Pageable pageable) {

        List<SearchHashtagDto> contents = queryFactory
                .select(
                        new QSearchHashtagDto(
                                post.count(),
                                hashtag.hashtagName
                        )
                )
                .from(hashtag)
                .leftJoin(hashtag.post, post)
                .where(hashtag.hashtagName.contains(keyword))
                .groupBy(hashtag.hashtagName)
                .orderBy(hashtag.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(
                        hashtag.count()
                )
                .from(hashtag)
                .groupBy(hashtag.hashtagName)
                .having(hashtag.hashtagName.contains(keyword))
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(contents, pageable, total);
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

}
