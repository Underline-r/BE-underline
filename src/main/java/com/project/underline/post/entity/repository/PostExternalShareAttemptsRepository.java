package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.PostExternalShareAttempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostExternalShareAttemptsRepository  extends JpaRepository<PostExternalShareAttempts, Long> {
    Optional<PostExternalShareAttempts> findByPost_PostIdAndShareTarget(Long postId,String shareTarget);
}
