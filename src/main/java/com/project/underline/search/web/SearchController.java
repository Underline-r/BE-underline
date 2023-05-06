package com.project.underline.search.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.search.service.SearchService;
import com.project.underline.search.web.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        SearchResponse searchResponse = new SearchResponse<>();

        switch (filterType) {
            case 0:
                searchResponse.setUnderlines(searchService.selectPostTitle(keyword, pageable));
                searchResponse.setUnderliners(searchService.selectUser(keyword, pageable));
                searchResponse.setSources(searchService.selectSource(keyword));
                searchResponse.setHashtags(searchService.selectHashTag(keyword));
                break;
            case 1:
                searchResponse.setUnderlines(searchService.selectPostTitle(keyword, pageable));
                break;
            case 2:
                searchResponse.setUnderliners(searchService.selectUser(keyword, pageable));
                break;
            case 3:
                searchResponse.setSources(searchService.selectSource(keyword));
                break;
            case 4:
                searchResponse.setHashtags(searchService.selectHashTag(keyword));
                break;
        }
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, searchResponse), HttpStatus.OK
        );
    }
}


