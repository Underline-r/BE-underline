package com.project.underline.post.entity.repository;

import com.project.underline.search.web.dto.SearchPostDto;
import com.project.underline.user.entity.User;
import com.project.underline.user.web.dto.UserPostDto;

import java.util.List;

public interface PostRepositoryCustom {

    List<UserPostDto> findAllByMyPick(User findUser);

    List<SearchPostDto> searchPostList(String keyword);
}
