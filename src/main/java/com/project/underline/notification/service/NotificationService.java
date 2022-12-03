package com.project.underline.notification.service;

import com.project.underline.common.util.SecurityUtil;
import com.project.underline.notification.entity.Notification;
import com.project.underline.notification.entity.repository.NotificationRepository;
import com.project.underline.notification.web.dto.NotificationResponse;
import com.project.underline.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    public List<NotificationResponse> inquiryNotification() {
        List<Notification> checkNotification = notificationRepository.findAllByHostId(SecurityUtil.getCurrentUserId());
        List<NotificationResponse> notifications = new ArrayList<NotificationResponse>();

        for (Notification notification: checkNotification) {
            // To-do. join으로 변경
            String guestNickname = userRepository.findById(notification.getGuestId()).get().getNickname();
            notifications.add(NotificationResponse.builder()
                            .notification(notification)
                            .guestNickname(guestNickname)
                    .build()
            );
        }

        return notifications;
    }
}
