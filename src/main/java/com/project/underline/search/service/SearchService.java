package com.project.underline.search.service;

import com.project.underline.post.entity.repository.PostRepository;
import com.project.underline.search.web.dto.SearchHashtagDto;
import com.project.underline.search.web.dto.SearchPostDto;
import com.project.underline.search.web.dto.SearchSourceDto;
import com.project.underline.search.web.dto.SearchUserDto;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<SearchUserDto> selectUser(String keyword, Pageable pageable) {
        return userRepository.searchUserProfile(keyword, pageable);
    }

    public List<SearchPostDto> selectPostTitle(String keyword, Pageable pageable) {
        return postRepository.searchPostList(keyword, pageable);
    }

    public List<SearchSourceDto> selectSource(String keyword) {
        return postRepository.searchSourceList(keyword);
    }

    public List<SearchHashtagDto> selectHashTag(String keyword) {
        return postRepository.searchHashtagList(keyword);
    }
}
