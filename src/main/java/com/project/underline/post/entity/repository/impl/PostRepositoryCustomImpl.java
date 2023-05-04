package com.project.underline.post.entity.repository.impl;

import com.project.underline.post.entity.repository.PostRepositoryCustom;
import com.project.underline.search.web.dto.*;
import com.project.underline.user.entity.User;
import com.project.underline.user.web.dto.QUserPostDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public List<SearchPostDto> searchPostList(String keyword, Pageable pageable) {
        String likeKeyword = "%" + keyword + "%";

        return queryFactory
                .select(
                        new QSearchPostDto(
                                post.postId,
                                post.content,
                                post.source.title,
                                post.user.nickname
                        )
                )
                .from(post)
                .where(post.content.like(likeKeyword))
                .offset(pageable.getOffset())
                .limit(10)
                .fetch();
    }

    @Override
    public List<SearchSourceDto> searchSourceList(String keyword) {
        String likeKeyword = "%" + keyword + "%";

        return queryFactory
                .select(
                        new QSearchSourceDto(
                                post.postId,
                                post.source.title,
                                post.user.id
                        )
                )
                .from(post)
                .join(post.source, source)
                .where(source.title.like(likeKeyword))
                .fetch();
    }

    @Override
    public List<SearchHashtagDto> searchHashtagList(String keyword) {
        String likeKeyword = "%" + keyword + "%";

        return queryFactory
                .select(
                        new QSearchHashtagDto(
                                hashtag.post.postId,
                                hashtag.hashtagName
                        )
                )
                .from(hashtag)
                // post id 를 이미 갖고 있음 check
//                .join(hashtag.post, post)
                .where(hashtag.hashtagName.like(likeKeyword))
                .fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

}
