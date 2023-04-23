package com.project.underline.reference.entity.repository;

import com.project.underline.search.web.dto.SearchReferenceDto;

import java.util.List;

public interface ReferenceRepositoryCustom {
    List<SearchReferenceDto> searchReferenceList(String keyword);
}
