package com.project.underline.search.service;

import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.search.web.dto.SearchHashtagDto;
import com.project.underline.search.web.dto.SearchPostDto;
import com.project.underline.search.web.dto.SearchSourceDto;
import com.project.underline.search.web.dto.SearchUserDto;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Page<SearchPostDto> selectPost(String keyword, Pageable pageable) {
        return postRepository.searchPostList(keyword, pageable);
    }

    public Page<SearchUserDto> selectUser(String keyword, Pageable pageable) {
        return userRepository.searchUserProfile(keyword, pageable);
    }

    public Page<SearchSourceDto> selectSource(String keyword, Pageable pageable) {
        return postRepository.searchSourceList(keyword, pageable);
    }

    public Page<SearchHashtagDto> selectHashTag(String keyword, Pageable pageable) {
        return postRepository.searchHashtagList(keyword, pageable);
    }
}
