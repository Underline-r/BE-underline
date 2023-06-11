package com.project.underline.search.service;

import com.project.underline.common.util.S3Service;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final S3Service s3Service;

    public Page<SearchPostDto> selectPost(String keyword, Pageable pageable) {
        Page<SearchPostDto> searchResult = postRepository.searchPostList(keyword, pageable);
        List<SearchPostDto> content = searchResult.getContent();

        for (SearchPostDto dto : content) {
            if (dto.getUserProfileImage() != null) {
                dto.setUserProfileImage(s3Service.getFilePath(dto.getUserProfileImage()));
            }
        }
        return searchResult;
    }

    public Page<SearchUserDto> selectUser(String keyword, Pageable pageable) {

        Page<SearchUserDto> searchResult = userRepository.searchUserProfile(keyword, pageable);
        List<SearchUserDto> content = searchResult.getContent();

        for (SearchUserDto dto : content) {
            if (dto.getUserProfileImage() != null) {
                dto.setUserProfileImage(s3Service.getFilePath(dto.getUserProfileImage()));
            }
        }

        return searchResult;
    }

    public Page<SearchSourceDto> selectSource(String keyword, Pageable pageable) {
        return postRepository.searchSourceList(keyword, pageable);
    }

    public Page<SearchHashtagDto> selectHashTag(String keyword, Pageable pageable) {
        return postRepository.searchHashtagList(keyword, pageable);
    }
}
