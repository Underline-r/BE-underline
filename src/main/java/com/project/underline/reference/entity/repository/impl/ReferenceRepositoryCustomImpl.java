package com.project.underline.reference.entity.repository.impl;

import com.project.underline.reference.entity.repository.ReferenceRepositoryCustom;
import com.project.underline.search.web.dto.SearchReferenceDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ReferenceRepositoryCustomImpl implements ReferenceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReferenceRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SearchReferenceDto> searchReferenceList(String keyword) {
        return null;
//        return queryFactory.select();
    }
}
