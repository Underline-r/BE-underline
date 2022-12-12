package com.project.underline.notification.web.dto;

import com.project.underline.notification.entity.Notification;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NotificationListResponse {
    private List<NotificationResponse> notificationList;

    // 이후 알람을 끊어서 조회할수도 있으니 따로 일급 컬렉션을 추가했습니다.
    @Builder
    public NotificationListResponse(List<Notification> notifications){
        this.notificationList = new ArrayList<NotificationResponse>();

        for (Notification notification: notifications) {
            notificationList.add(NotificationResponse
                    .builder()
                    .notifications(notification)
                    .build()
            );
        }
    }


}
