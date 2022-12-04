package com.project.underline.notification.entity.repository;

import com.project.underline.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByHostId(Long hostId);
    Notification findByNotificationId(Long notificationId);
}
