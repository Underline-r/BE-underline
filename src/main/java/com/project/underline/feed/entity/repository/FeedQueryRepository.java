package com.project.underline.feed.entity.repository;

import com.project.underline.bookmark.entity.QBookmark;
import com.project.underline.category.entity.QPostCategoryRelation;
import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.feed.web.dto.FeedResponse;
import com.project.underline.feed.web.dto.QFeedPost;
import com.project.underline.post.entity.QComment;
import com.project.underline.post.entity.QPick;
import com.project.underline.post.entity.QPost;
import com.project.underline.reference.entity.QReference;
import com.project.underline.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FeedQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    public FeedResponse getCustomFeedInformation(FeedResponse feedResponse, Pageable pageable, List<String> categoryCode){
        //TO-Do. 초반엔 구독중인 카테고리에 올라온 글들을 제공해주지만 mvp이후 팔로우한 사람들의 글도 섞어서 제공할 예정

        QPost p = QPost.post;
        QUser u = QUser.user;
        QPick pp = QPick.pick;
        QComment c = QComment.comment;
        QPostCategoryRelation pcr = QPostCategoryRelation.postCategoryRelation;
        QReference ref = QReference.reference;

        feedResponse.setFeedPosts(
                queryFactory.select(new QFeedPost(
                        p.postId,
                        u.nickname,
                        u.imagePath,
                        u.id,
                        p.content,
                        c.commentId.countDistinct(),
                        pp.pickId.countDistinct(),
                        ref.title,
                        p.createdDate)
                        )
                .from(p)
                .where(pcr.categoryCode.in(categoryCode))
                .join(u).on(p.user.eq(u))
                .leftJoin(pcr).on(pcr.post.eq(p))
                .leftJoin(pp).on(pp.post.eq(p))
                .leftJoin(c).on(c.post.eq(p))
                .leftJoin(ref).on(p.reference.eq(ref))
                .groupBy(p.postId)
                .orderBy(p.createdDate.desc())
                .limit(10)
                .offset(pageable.getOffset())
                .fetch()
        );

        return feedResponse;
    }

    public FeedResponse setUserPickOrBookmark(FeedResponse feedResponse){

        QUser u = QUser.user;
        QBookmark bm = QBookmark.bookmark;
        QPick pp = QPick.pick;


        List<Long> postIdList = new ArrayList<Long>();

        for (FeedPost eachPost :feedResponse.getFeed()) {
            postIdList.add(eachPost.getPostId());
        }


        feedResponse.setPickYn(queryFactory
                .select(pp.post.postId)
                .from(u)
                .join(pp).on(pp.user.eq(u))
                .where(pp.post.postId.in(postIdList))
                .fetch()
        );

        feedResponse.setBookmarkYn(queryFactory
                .select(bm.post.postId)
                .from(u)
                .join(bm).on(bm.user.eq(u))
                .where(bm.post.postId.in(postIdList))
                .fetch()
        );

        return feedResponse;
    }

    public FeedResponse getDefaultFeedInformation(FeedResponse feedResponse, Pageable pageable){
        //TO-Do. 초반엔 구독중인 카테고리에 올라온 글들을 제공해주지만 mvp이후 팔로우한 사람들의 글도 섞어서 제공할 예정

        QPost p = QPost.post;
        QUser u = QUser.user;
        QPick pp = QPick.pick;
        QComment c = QComment.comment;
        QPostCategoryRelation pcr = QPostCategoryRelation.postCategoryRelation;
        QReference ref = QReference.reference;

        feedResponse.setFeedPosts(queryFactory.select(new QFeedPost(
                        p.postId,
                        u.nickname,
                        u.imagePath,
                        u.id,
                        p.content,
                        c.commentId.countDistinct(),
                        pp.pickId.countDistinct(),
                        ref.title,
                        p.createdDate)
                )
                .from(p)
                .join(u).on(p.user.eq(u))
                .leftJoin(pcr).on(pcr.post.eq(p))
                .leftJoin(pp).on(pp.post.eq(p))
                .leftJoin(c).on(c.post.eq(p))
                .leftJoin(ref).on(p.reference.eq(ref))
                .groupBy(p.postId)
                .orderBy(p.createdDate.desc())
                .limit(10)
                .offset(pageable.getOffset())
                .fetch()
        );

        return feedResponse;
    }
}
