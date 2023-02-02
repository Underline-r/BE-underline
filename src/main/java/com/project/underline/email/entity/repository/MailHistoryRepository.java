package com.project.underline.email.entity.repository;

import com.project.underline.email.entity.MailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailHistoryRepository extends JpaRepository<MailHistory, Long> {
}
