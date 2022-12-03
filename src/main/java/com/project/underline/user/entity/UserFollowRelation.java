package com.project.underline.user.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="follow_relation_uk",
                        columnNames = {"toUserId", "from_user_id"}
                )
        }
)
public class UserFollowRelation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long toUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    public UserFollowRelation(Long toUserId, User fromUser) {
        this.toUserId = toUserId;
        this.fromUser = fromUser;
    }
}
