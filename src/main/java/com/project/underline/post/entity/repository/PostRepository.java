package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> , PostRepositoryCustom{
    Optional<Post> findByPostId(Long postId);

    Page<Post> findAll(Pageable pageable);

    List<Post> findAllByUserId(Long userId);

}
