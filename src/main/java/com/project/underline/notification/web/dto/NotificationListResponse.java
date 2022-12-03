package com.project.underline.notification.web.dto;

import com.project.underline.notification.entity.Notification;
import lombok.Getter;

import java.util.List;

@Getter
public class NotificationListResponse {
    List<NotificationResponse> notifications;

    public NotificationListResponse(List<Notification> notifications){
        for (Notification notification: notifications) {
        }
    }
}
