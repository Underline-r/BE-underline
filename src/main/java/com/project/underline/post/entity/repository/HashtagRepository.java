package com.project.underline.post.entity.repository;

import com.project.underline.post.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findFirstByHashtagNameOrderByModifiedDateDesc(String hashtagName);
}
