package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Comment;
import com.project.underline.post.entity.Post;
import com.project.underline.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User findUser);
}
