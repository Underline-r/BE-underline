package com.project.underline.search.service;

import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.reference.entity.repository.ReferenceRepository;
import com.project.underline.search.web.dto.SearchHashtagDto;
import com.project.underline.search.web.dto.SearchPostDto;
import com.project.underline.search.web.dto.SearchReferenceDto;
import com.project.underline.search.web.dto.SearchUserDto;
import com.project.underline.user.entity.repository.UserRepository;
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

    public List<SearchUserDto> selectUser(String keyword) {
        return userRepository.searchUserProfile(keyword);
    }

    public List<SearchPostDto> selectPostTitle(String keyword) {
        return postRepository.searchPostList(keyword);
    }

    public List<SearchReferenceDto> selectReference(String keyword) {
        return referenceRepository.searchReferenceList(keyword);
    }

    public List<SearchHashtagDto> selectHashTag(String keyword) {
        return hashtagRepository.searchHashtagList(keyword);
    }
}
