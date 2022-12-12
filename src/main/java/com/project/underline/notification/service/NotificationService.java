package com.project.underline.notification.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.notification.entity.Notification;
import com.project.underline.notification.entity.repository.NotificationRepository;
import com.project.underline.notification.web.dto.NotificationCheckRequest;
import com.project.underline.notification.web.dto.NotificationListResponse;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationListResponse inquiryNotification() {
        List<Notification> checkNotification = notificationRepository.findAllByHostId(SecurityUtil.getCurrentUserId());
        NotificationListResponse notifications = new NotificationListResponse(checkNotification);

        return notifications;
    }

    public void checkNotification(NotificationCheckRequest notificationCheckRequest) {

        Notification updateTarget = notificationRepository.findByNotificationId(notificationCheckRequest.getNotificationId());
        updateTarget.checkNotification();

        notificationRepository.save(updateTarget);
    }
}
