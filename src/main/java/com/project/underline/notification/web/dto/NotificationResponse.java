package com.project.underline.notification.web.dto;

import com.project.underline.common.metadata.SearchYn;
import com.project.underline.notification.entity.Notification;
import com.project.underline.notification.metadata.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationResponse {
    private Long notificationId;
    private String guestNickname;
    private Long postId;
    private NotificationType notificationType;
    private SearchYn searchYn;

    @Builder
    public NotificationResponse(Notification notification, String guestNickname){
        this.notificationId = notification.getNotificationId();
        this.guestNickname = guestNickname;
        this.postId = notification.getPostId();
        this.notificationType = notification.getNotificationType();
        this.searchYn = notification.getSearchYn();
    }
}
