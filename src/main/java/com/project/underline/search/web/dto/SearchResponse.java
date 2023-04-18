package com.project.underline.search.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse<T> {

    public List<T> userResult;
    public List<T> postResult;
    public List<T> referenceResult;
    public List<T> hashtagResult;
}
