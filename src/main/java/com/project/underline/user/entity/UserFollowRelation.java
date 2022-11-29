package com.project.underline.user.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserFollowRelation extends BaseTimeEntity {
    // TODO : 유니크 제약조건 DDL 추가 : (to_user_id, from_user_id)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long toUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser;
}
