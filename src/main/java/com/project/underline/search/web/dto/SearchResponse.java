package com.project.underline.search.web.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchResponse {
    public Page underlines;
    public Page underliners;
    public Page sources;
    public Page hashtags;

    public SearchResponse(Page underlines, Page underliners, Page sources, Page hashtags) {
        this.underlines = underlines;
        this.underliners = underliners;
        this.sources = sources;
        this.hashtags = hashtags;
    }
}
