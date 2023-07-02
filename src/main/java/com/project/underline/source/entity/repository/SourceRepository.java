package com.project.underline.source.entity.repository;

import com.project.underline.source.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SourceRepository extends JpaRepository<Source,Long> {
    Optional<Source> findByTitle(String title);
    Optional<Source> findFirstByTitleOrderByModifiedDateDesc(String title);
}
