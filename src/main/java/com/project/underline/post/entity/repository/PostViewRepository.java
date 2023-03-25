package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    Optional<PostView> findByPost_PostId(Long postId);
}
