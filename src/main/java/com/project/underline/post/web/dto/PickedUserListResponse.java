package com.project.underline.post.web.dto;

import com.project.underline.post.entity.Pick;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PickedUserListResponse {
    private Long total;
    private List<String> userNicknameList;

    public void pickUserListUp(List<Pick> pickedUserList){
        this.userNicknameList = new ArrayList<String>();

        for (Pick eachPicked : pickedUserList) {
            this.userNicknameList.add(eachPicked.getUser().getNickname());
        }

        this.total = Long.valueOf(pickedUserList.size());
    }
}
