package com.project.underline.post.entity.repository.impl;

import com.project.underline.post.entity.repository.HashtagRepositoryCustom;
import com.project.underline.search.web.dto.SearchHashtagDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class HashtagRepositoryCustomImpl implements HashtagRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    public HashtagRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SearchHashtagDto> searchHashtagList(String keyword) {
        return null;

//        return queryFactory.select()
    }
}
