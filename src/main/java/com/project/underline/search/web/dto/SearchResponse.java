package com.project.underline.search.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse<T> {

    public List<T> underliners;
    public List<T> underlines;
    public List<T> sources;
    public List<T> hashtags;
}
