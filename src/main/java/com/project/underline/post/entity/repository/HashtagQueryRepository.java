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
import static com.project.underline.post.web.dto.HashtagSearchResponse.OtherContent.OtherHashtag;
import static com.project.underline.post.web.dto.HashtagSearchResponse.TargetContent.TargetHashtag;
import static com.project.underline.user.entity.QUser.user;

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
                                post.postId.count().as("pickCount"),
                                post.content,
                                post.title,
                                user.nickname
                        )
                )
                .from(hashtag)
                .join(hashtag.post, post)
                .join(post.user, user)
                .leftJoin(pick.post,post)
                .where(hashtag.hashtagName.eq(keyword))
                .groupBy(post.postId)
                .fetch();
    }

    public List<TargetHashtag> getHashtagListFromTarget(String keyword, Long userId){
//        select * from post left join hashtag on hashtag.POST_ID = post.POST_ID where hashtag.hashtag_name = 'hashtag1';
        return queryFactory
                .select(new QHashtagSearchResponse_TargetContent_TargetHashtag(
                        post.postId,
                        post.postId.count().as("pickCount"),
                        post.content,
                        post.title
                )
        )
        .from(hashtag)
        .join(hashtag.post, post) // Q. post 테이블 기준으로 짜면 에러메세지 나던데 왜그런거죠? -> hashtag.post is not a root path; nested exception is java.lang.illegalargumentexception: hashtag.post is not a root path
        .leftJoin(pick.post,post)
        .where(hashtag.hashtagName.eq(keyword), post.user.id.eq(userId))
        .fetch();
    }

}
