package com.project.underline.post.entity.repository.impl;

import com.project.underline.post.entity.repository.PostRepositoryCustom;
import com.project.underline.post.web.dto.PostSearchDto;
import com.project.underline.post.web.dto.QPostSearchDto;
import com.project.underline.user.entity.User;
import com.project.underline.user.web.dto.QUserPostDto;
import com.project.underline.user.web.dto.UserPostDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.underline.post.entity.QPick.pick;
import static com.project.underline.post.entity.QPost.post;
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
    public List<PostSearchDto> searchPostList(String keyword) {
        return queryFactory
                .select(
                        new QPostSearchDto(
                                post.postId,
                                post.content,
                                post.createdDate
                        )
                )
                .from(post)
                .fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

}
