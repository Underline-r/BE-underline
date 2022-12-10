package com.project.underline.notification.entity;

import com.project.underline.common.metadata.SearchYn;
import com.project.underline.notification.metadata.NotificationType;
import com.project.underline.post.entity.Post;
import com.project.underline.user.entity.User;
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

    @ManyToOne
    @JoinColumn(name = "HOST_ID")
    private User host;

    @ManyToOne
    @JoinColumn(name = "GUEST_ID")
    private User guest;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name="NOTIFICATION_TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name="SEARCH_YN")
    @Enumerated(EnumType.STRING)
    private SearchYn searchYn;

    @Builder
    public Notification(Long notificationId,User host,User guest,Post post,NotificationType notificationType,SearchYn searchYn){
        this.notificationId = notificationId;
        this.host = host;
        this.guest = guest;
        this.post = post;
        this.notificationType = notificationType;
        this.searchYn = SearchYn.N; // 생성시 Default = N(안읽었음)
    }

    public Notification checkNotification(){
        this.searchYn = SearchYn.Y;
        return this;
    }
}
