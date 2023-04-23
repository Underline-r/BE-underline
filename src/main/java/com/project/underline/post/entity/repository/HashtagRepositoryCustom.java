package com.project.underline.post.entity.repository;

import com.project.underline.search.web.dto.SearchHashtagDto;

import java.util.List;

public interface HashtagRepositoryCustom {
    List<SearchHashtagDto> searchHashtagList(String keyword);

}
