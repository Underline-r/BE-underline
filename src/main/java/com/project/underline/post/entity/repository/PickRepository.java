package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Pick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PickRepository extends JpaRepository<Pick, Long> {

    Optional<Pick> findByPostIdAndUserId(Long postId, Long userId);
}
