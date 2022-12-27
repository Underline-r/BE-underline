package com.project.underline.post.entity.repository;

import com.project.underline.post.web.dto.QHashtagSearchResponse_OtherContent_OtherHashtag;
import com.project.underline.post.web.dto.QHashtagSearchResponse_TargetContent_TargetHashtag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.underline.post.entity.QHashtag.hashtag;
import static com.project.underline.post.entity.QPost.post;
import static com.project.underline.post.entity.QPick.pick;
import static com.project.underline.user.entity.QUser.user;
import static com.project.underline.post.entity.QComment.comment;
import static com.project.underline.post.web.dto.HashtagSearchResponse.OtherContent.OtherHashtag;
import static com.project.underline.post.web.dto.HashtagSearchResponse.TargetContent.TargetHashtag;

@Repository
public class HashtagQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HashtagQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<OtherHashtag> getHashtagListFromOther(String keyword){
        return queryFactory
                .select(new QHashtagSearchResponse_OtherContent_OtherHashtag(
                        post.postId,
                        post.content,
                        post.title,
                        user.nickname,
                        pick.pickId.count().as("pickCount"),
                        comment.commentId.count().as("commentCount")
                        )
                )
                .from(hashtag)
                .leftJoin(post).on(post.postId.eq(hashtag.post.postId))
                .leftJoin(pick).on(post.postId.eq(pick.post.postId))
                .leftJoin(comment).on(post.postId.eq(comment.post.postId))
                .leftJoin(user).on(post.user.id.eq(user.id))
                .where(hashtag.hashtagName.eq(keyword))
                .groupBy(post.postId)
                .fetch();
    }

    public List<TargetHashtag> getHashtagListFromTarget(String keyword, Long userId){
//        select * from post left join hashtag on hashtag.POST_ID = post.POST_ID where hashtag.hashtag_name = 'hashtag1';
        return queryFactory
                .select(new QHashtagSearchResponse_TargetContent_TargetHashtag(
                        post.postId,
                        post.content,
                        post.title,
                        pick.pickId.count().as("pickCount"),
                        comment.commentId.count().as("commentCount")
                )
        )
                .from(hashtag)
                .leftJoin(post).on(post.postId.eq(hashtag.post.postId))
                .leftJoin(pick).on(post.postId.eq(pick.post.postId))
                .leftJoin(comment).on(post.postId.eq(comment.post.postId))
                .leftJoin(user).on(post.user.id.eq(user.id))
                .where(hashtag.hashtagName.eq(keyword), user.id.eq(userId))
                .groupBy(post.postId)
                .fetch();
    }

}
