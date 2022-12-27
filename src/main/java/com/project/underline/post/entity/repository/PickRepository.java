package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PickRepository extends JpaRepository<Pick, Long> {

    @Modifying
    @Query(value = "select Pick from Pick where post.postId = :postId and user.id =:userId", nativeQuery = true)
    Optional<Pick> findByPostIdAndUserId(Long postId, Long userId);

    @Modifying
    @Query(value = "select Pick from Pick where post.postId = :postId", nativeQuery = true)
    List<Pick> findAllByPostId(Long postId);
}
