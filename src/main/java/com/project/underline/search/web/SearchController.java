package com.project.underline.search.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.search.service.SearchService;
import com.project.underline.search.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    /**
     * @param filterType
     * 0 : 전체
     * 1 : underline
     * 2 : underliner
     * 3 : source
     * 4 : hashtag
     */
    @GetMapping()
    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, Integer filterType, Pageable pageable) {
        keyword = keyword.trim();

        Page<SearchPostDto> postResult = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<SearchUserDto> userResult = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<SearchSourceDto> sourceResult = new PageImpl<>(new ArrayList<>(), pageable, 0);
        Page<SearchHashtagDto> hashtagResult = new PageImpl<>(new ArrayList<>(), pageable, 0);
        switch (filterType) {
            case 0:
                postResult = searchService.selectPost(keyword, pageable);
                userResult = searchService.selectUser(keyword, pageable);
                sourceResult = searchService.selectSource(keyword, pageable);
                hashtagResult = searchService.selectHashTag(keyword, pageable);
                break;
            case 1:
                postResult = searchService.selectPost(keyword, pageable);
                break;
            case 2:
                userResult = searchService.selectUser(keyword, pageable);
                break;
            case 3:
                sourceResult = searchService.selectSource(keyword, pageable);
                break;
            case 4:
                hashtagResult = searchService.selectHashTag(keyword, pageable);
                break;
        }

        SearchResponse searchResponse = new SearchResponse(postResult, userResult, sourceResult, hashtagResult);

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, searchResponse), HttpStatus.OK
        );
    }
}


