package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Comment;
import com.project.underline.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    Optional<List<Comment>> findAllByPost_PostId(Long postId);
}
