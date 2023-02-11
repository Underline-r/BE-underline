package com.project.underline.feed.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.feed.entity.repository.FeedQueryRepository;
import com.project.underline.feed.web.dto.FeedPost;
import com.project.underline.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebFeedService {
    private final PostRepository postRepository;
    private final UserCategoryRelationRepository userCategoryRelationRepository;
    private final FeedQueryRepository feedQueryRepository;

    @Transactional
    public List<FeedPost> makeWebFeed(Pageable pageable) {
        List<UserCategoryRelation> likedCategoryCodeList = userCategoryRelationRepository.findAllByUserId(SecurityUtil.getCurrentUserId());
        return feedQueryRepository.getCustomFeedInformation(pageable,categoryListToString(likedCategoryCodeList));
    }

    private List<String> categoryListToString(List<UserCategoryRelation> categoryList){
        List<String> stringCategoryList = new ArrayList<String>();

        for (UserCategoryRelation eachCategory: categoryList) {
            stringCategoryList.add(eachCategory.getCategory());

        }
        return stringCategoryList;
    }
}
