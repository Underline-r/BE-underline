package com.project.underline.category.entity.repository;

import com.project.underline.category.entity.UserCategoryRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRelationRepository extends JpaRepository<UserCategoryRelation, Long> {
    List<UserCategoryRelation> findAllByUserId(Long userId);
}
