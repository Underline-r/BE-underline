package com.project.underline.search.service;

import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.post.web.dto.HashtagSearchResponse;
import com.project.underline.post.web.dto.PostSearchDto;
import com.project.underline.reference.entity.repository.ReferenceRepository;
import com.project.underline.user.entity.repository.UserRepository;
import com.project.underline.user.web.dto.UserProfileDto;
import com.project.underline.user.web.dto.UserReferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReferenceRepository referenceRepository;
    private final HashtagRepository hashtagRepository;

    public List<UserProfileDto> selectUser(String keyword) {
        return userRepository.searchUserProfile(keyword);
    }

    public List<PostSearchDto> selectPostTitle(String keyword) {
        return postRepository.searchPostList(keyword);
    }

    public List<UserReferenceDto> selectReference(String keyword) {
        return null;
    }

    public List<HashtagSearchResponse> selectHashTag(String keyword) {
        return null;
    }
}
