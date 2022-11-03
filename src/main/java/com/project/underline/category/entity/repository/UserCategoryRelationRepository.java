package com.project.underline.category.entity.repository;

import com.project.underline.category.entity.UserCategoryRelation;
import com.project.underline.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRelationRepository extends JpaRepository<UserCategoryRelation, Long> {
}
