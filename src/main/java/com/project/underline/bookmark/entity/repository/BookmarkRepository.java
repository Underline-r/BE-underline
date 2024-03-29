package com.project.underline.bookmark.entity.repository;

import com.project.underline.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    List<Bookmark> findAllByUserId(Long userId);
    Boolean existsByPost_PostIdAndUser_Id(Long postId, Long userId);

    void deleteBookmarkByPost_PostIdAndUser_Id(Long postId, Long userId);
}
