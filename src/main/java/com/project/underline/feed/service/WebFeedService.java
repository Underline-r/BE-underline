package com.project.underline.feed.service;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.category.entity.repository.UserCategoryRelationRepository;
import com.project.underline.common.util.SecurityUtil;
import com.project.underline.post.entity.Post;
import com.project.underline.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebFeedService {
    private final PostRepository postRepository;
    private final UserCategoryRelationRepository userCategoryRelationRepository;
    @Transactional
    public Page<Post> makeWebFeed(Pageable pageable) {
        List<UserCategoryRelation> likedCategoryCodeList = userCategoryRelationRepository.findAllByUserId(SecurityUtil.getCurrentUserId());
        Page<Post> webFeed = postRepository.findAll(pageable);
        return webFeed;
    }
}
