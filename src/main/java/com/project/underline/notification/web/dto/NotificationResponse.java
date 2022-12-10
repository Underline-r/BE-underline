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
    public NotificationResponse(Notification notifications){
        this.notificationId = notifications.getNotificationId();
        this.guestNickname = notifications.getGuest().getNickname();
        this.postId = notifications.getPost().getPostId();
        this.notificationType = notifications.getNotificationType();
        this.searchYn = notifications.getSearchYn();
    }
}
