package com.project.underline.post.service;

import com.project.underline.post.entity.repository.HashtagQueryRepository;
import com.project.underline.post.entity.repository.HashtagRepository;
import com.project.underline.post.web.dto.HashtagSearchResponse;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final HashtagQueryRepository hashtagQueryRepository;

    public HashtagSearchResponse searchBasedOnHashtag(String keyword, String userNickname) {

        Long targetUserId = userRepository.findByNickname(userNickname).getId();
        HashtagSearchResponse hashtagSearchResponse = new HashtagSearchResponse();

        // 요청왔던 데이터 그대로 return
        hashtagSearchResponse.setBasicInfo(keyword,userNickname);

        // Post테이블을 기준으로 hashtag와 user테이블을 join해야함 -> targetContent
        hashtagSearchResponse.setTargetContent(hashtagQueryRepository.getHashtagListFromTarget(keyword,targetUserId));
        hashtagSearchResponse.setOtherContent(hashtagQueryRepository.getOtherHashtagList(keyword));


        return hashtagSearchResponse;
    }
}
