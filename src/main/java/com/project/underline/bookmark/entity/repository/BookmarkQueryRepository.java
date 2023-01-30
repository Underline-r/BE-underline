package com.project.underline.bookmark.entity.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BookmarkQueryRepository {
    private final JPAQueryFactory queryFactory;

    public BookmarkQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
