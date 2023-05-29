package com.project.underline.search.web.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class SearchResponse {
    public List underlines;
    public Page underliners;
    public List sources;
    public List hashtags;

    public SearchResponse(List underlines, Page underliners, List sources, List hashtags) {
        this.underlines = underlines;
        this.underliners = underliners;
        this.sources = sources;
        this.hashtags = hashtags;
    }
}
