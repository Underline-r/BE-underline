package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Pick;
import com.project.underline.post.entity.Post;
import com.project.underline.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PickRepository extends JpaRepository<Pick, Long> {

    List<Pick> findAllByPost(Post post);

    Optional<Pick> findByPostAndUser(Post post, User user);
    Boolean existsByPost_PostIdAndUser_Id(Long postId, Long userId);

    Long countAllByPost_PostId(Long postId);
}
