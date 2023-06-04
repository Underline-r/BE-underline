package com.project.underline.post.entity.repository;

import com.project.underline.search.web.dto.SearchHashtagDto;
import com.project.underline.search.web.dto.SearchPostDto;
import com.project.underline.search.web.dto.SearchSourceDto;
import com.project.underline.user.entity.User;
import com.project.underline.user.web.dto.UserPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<UserPostDto> findAllByMyPick(User findUser);

    Page<SearchPostDto> searchPostList(String keyword, Pageable pageable);

    Page<SearchSourceDto> searchSourceList(String keyword, Pageable pageable);

    Page<SearchHashtagDto> searchHashtagList(String keyword, Pageable pageable);
}
