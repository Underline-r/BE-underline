package com.project.underline.search.web;

import com.project.underline.common.metadata.ResponseMessage;
import com.project.underline.common.payload.DefaultResponse;
import com.project.underline.post.web.dto.PostSearchDto;
import com.project.underline.search.service.SearchService;
import com.project.underline.search.web.dto.SearchResponse;
import com.project.underline.user.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping()
    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, String filterType) {
        SearchResponse searchResponse = new SearchResponse<>();

        switch (filterType) {
            case "USER_NAME":
                List<UserProfileDto> userProfileDtos = searchService.selectUser(keyword);
                searchResponse.setUserResult(userProfileDtos);
                break;
            case "TITLE":
                List<PostSearchDto> postSearchDtos = searchService.selectPostTitle(keyword);
                searchResponse.setPostResult(postSearchDtos);
                break;
            case "REFERENCE":
                searchService.selectReference(keyword);
                break;
            case "HASHTAG":
                searchService.selectHashTag(keyword);
                break;
//            case "CONTENT":
//                searchService.select
        }


        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(),  ResponseMessage.SUCCESS, searchResponse), HttpStatus.OK
        );
    }

    @GetMapping("/preview")
    public ResponseEntity<SearchResponse> searchPreview(@RequestParam String keyword) {

        SearchResponse searchResponse = new SearchResponse<>();

        switch (keyword) {
            case "USER_NAME":
                searchService.selectUser(keyword);
                break;
            case "TITLE":
                searchService.selectPostTitle(keyword);
                break;
            case "REFERENCE":
                searchService.selectReference(keyword);
                break;
            case "HASHTAG":
                searchService.selectHashTag(keyword);
                break;
//            case "CONTENT":
//                searchService.select
        }

        return new ResponseEntity(
                DefaultResponse.res(HttpStatus.OK.value(), ResponseMessage.SUCCESS, null), HttpStatus.OK
        );
    }
}


