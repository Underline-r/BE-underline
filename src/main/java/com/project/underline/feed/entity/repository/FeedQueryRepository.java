package com.project.underline.feed.entity.repository;

import com.project.underline.category.entity.QPostCategoryRelation;
import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.feed.web.dto.QFeedPost;
import com.project.underline.post.entity.QComment;
import com.project.underline.post.entity.QHashtag;
import com.project.underline.post.entity.QPick;
import com.project.underline.post.entity.QPost;
import com.project.underline.user.entity.QUser;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FeedQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FeedQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
     public List<FeedPost> getCustomFeedInformation(Pageable pageable,List<String> categoryCode){
        //TO-Do. 초반엔 구독중인 카테고리에 올라온 글들을 제공해주지만 mvp이후 팔로우한 사람들의 글도 섞어서 제공할 예정

        QPost p = QPost.post;
        QUser u = QUser.user;
        QPick pp = QPick.pick;
        QComment c = QComment.comment;
        QHashtag h = QHashtag.hashtag;
        QPostCategoryRelation pcr = QPostCategoryRelation.postCategoryRelation;

        return queryFactory.select(new QFeedPost(
                        p.postId,
                        u.nickname,
                        p.content,
                        c.commentId.countDistinct(),
                        pp.pickId.countDistinct(),
                        Expressions.simpleTemplate(String.class, "group_concat({0})", h.hashtagName)))
                .from(p)
                .where(pcr.categoryCode.in(categoryCode))
                .join(u).on(p.user.eq(u))
                .join(pcr).on(pcr.post.eq(p))
                .leftJoin(pp).on(pp.post.eq(p))
                .leftJoin(c).on(c.post.eq(p))
                .leftJoin(h).on(h.post.eq(p))
                .groupBy(p.postId)
                .orderBy(p.createdDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

    }
}
