package com.project.underline.email.entity;

import com.project.underline.common.util.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class MailHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String recipient;

    private Boolean successFlag;

    private String logMessage;

    public MailHistory(String email, Boolean successFlag, String logMessage) {
        this.recipient = email;
        this.successFlag = successFlag;
        this.logMessage = logMessage;
    }
}
