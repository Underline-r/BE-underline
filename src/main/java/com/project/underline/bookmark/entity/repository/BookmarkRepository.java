package com.project.underline.bookmark.entity.repository;

import com.project.underline.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    List<Bookmark> findAllByUserId(Long userId);
}
