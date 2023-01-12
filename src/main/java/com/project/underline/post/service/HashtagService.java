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

        // TODO. 이거 쿼리 한번만 나가게 리팩토링 가능할것같은데 (자바단에서 처리하도록)

        // 해당 해시태그 기준으로 target의 정보 set
        hashtagSearchResponse.setTargetContent(hashtagQueryRepository.getHashtagListFromTarget(keyword,targetUserId));

        // 해당 해시태그 기준으로 다른사람들의 게시글 정보 set
        hashtagSearchResponse.setOtherContent(hashtagQueryRepository.getHashtagListFromOther(keyword));


        return hashtagSearchResponse;
    }
}
