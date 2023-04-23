package com.project.underline.search.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.search.service.SearchService;
import com.project.underline.search.web.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, String filterType) {
        SearchResponse searchResponse = new SearchResponse<>();

        // TODO: null 조건 프론트랑 재확인
        if(filterType == null || "".equals(filterType)) {
            filterType = "WHOLE";
        }
        switch (filterType) {
            case "WHOLE":
                searchResponse.setUnderliners(searchService.selectUser(keyword));
                searchResponse.setUnderlines(searchService.selectPostTitle(keyword));
                searchResponse.setSources(searchService.selectReference(keyword));
                searchResponse.setHashtags(searchService.selectHashTag(keyword));
                break;
            case "USER_NAME":
                searchResponse.setUnderliners(searchService.selectUser(keyword));
                break;
            case "TITLE":
                searchResponse.setUnderlines(searchService.selectPostTitle(keyword));
                break;
            case "REFERENCE":
                searchResponse.setSources(searchService.selectReference(keyword));
                break;
            case "HASHTAG":
                searchResponse.setHashtags(searchService.selectHashTag(keyword));
                break;
        }
        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, searchResponse), HttpStatus.OK
        );
    }
}


