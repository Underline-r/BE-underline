package com.project.underline.category.entity.repository;

import com.project.underline.category.entity.UserCategoryRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCategoryRelationRepository extends JpaRepository<UserCategoryRelation, Long> {
    List<UserCategoryRelation> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from UserCategoryRelation ucr where ucr.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}
