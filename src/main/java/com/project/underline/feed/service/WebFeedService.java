package com.project.underline.feed.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import com.project.underline.common.exception.UnderlineException;
import com.project.underline.common.jwt.JwtFilter;
import com.project.underline.common.jwt.JwtTokenProvider;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.feed.entity.repository.FeedQueryRepository;
import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.feed.web.dto.FeedResponse;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.service.PostViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebFeedService {
    private final PostRepository postRepository;
    private final UserCategoryRelationRepository userCategoryRelationRepository;
    private final FeedQueryRepository feedQueryRepository;
    private final PostViewService postViewService;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public FeedResponse makeWebFeed(Pageable pageable) {
        FeedResponse feedResponse = new FeedResponse();
        Long userId = SecurityUtil.getCurrentUserId();

        try {
            return loginUserFeed(feedResponse, pageable, userId);
        } catch (UnderlineException e) {
            throw e;
        }
    }

    private Long tokenReceive(HttpServletRequest request) {

        String accessToken = request.getHeader(JwtFilter.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(JwtFilter.BEARER_PREFIX)) {
            accessToken.substring(7);
        }

        if (accessToken != null) {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Long userId = SecurityUtil.getCurrentUserId();
            return userId;
        } else {
            return null;
        }
    }

    private FeedResponse notLoginUserFeed(FeedResponse feedResponse, Pageable pageable) {
        feedQueryRepository.getDefaultFeedInformation(feedResponse, pageable);
        return feedResponse;
    }

    private FeedResponse loginUserFeed(FeedResponse feedResponse, Pageable pageable, Long userId) {
        Optional<List<UserCategoryRelation>> likedCategoryCodeList = userCategoryRelationRepository
                .findAllByUserId(userId);

        if (likedCategoryCodeList == null || likedCategoryCodeList.get().size() < 1) {
            feedQueryRepository.getDefaultFeedInformation(feedResponse, pageable);
        } else {
            feedQueryRepository.getCustomFeedInformation(feedResponse, pageable, categoryListToString(likedCategoryCodeList.get()));
        }
        feedQueryRepository.setUserPickOrBookmark(feedResponse);

        feedResponse.isLastCheck();
        postViewIncrease(feedResponse.getFeed());


        return feedResponse;
    }

    private void postViewIncrease(List<FeedPost> feedPosts){
        postViewService.postListViewIncrease(feedPosts);
    }

    private List<String> categoryListToString(List<UserCategoryRelation> categoryList){
        List<String> stringCategoryList = new ArrayList<String>();

        for (UserCategoryRelation eachCategory: categoryList) {
            stringCategoryList.add(eachCategory.getCategory());

        }
        return stringCategoryList;
    }
}
