package com.project.underline.post.web.dto;

import com.project.underline.user.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class PickedUserListResponse {
    private Long total;
    private List<String> userNicknameList;

    public void pickUserListUp(List<User> userList){
        // 닉네임을 return 해야되니 Pick 객체 -> User 객체 변환은 서비스단에서 해결
        for (User eachPicked : userList) {
            this.userNicknameList.add(eachPicked.getNickname());
        }

        this.total = Long.valueOf(userList.size());
    }
}
