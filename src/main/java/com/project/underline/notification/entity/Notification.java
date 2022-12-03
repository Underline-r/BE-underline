package com.project.underline.notification.entity;

import com.project.underline.common.metadata.SearchYn;
import com.project.underline.notification.metadata.NotificationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue
    @Column(name="NOTIFICATION_ID")
    private Long notificationId;

    @Column(name="HOST_ID")
    private Long hostId;

    @Column(name="GUEST_ID")
    private Long guestId;

    @Column(name="POST_ID")
    private Long postId;

    @Column(name="NOTIFICATION_TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name="SEARCH_YN")
    @Enumerated(EnumType.STRING)
    private SearchYn searchYn;

    @Builder
    public Notification(Long notificationId,Long hostId,Long guestId,Long postId,NotificationType notificationType,SearchYn searchYn){
        this.notificationId = notificationId;
        this.hostId = hostId;
        this.guestId = guestId;
        this.postId = postId;
        this.notificationType = notificationType;
        this.searchYn = searchYn;
    }
}
