package com.project.underline.reference.entity.repository;

import com.project.underline.reference.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceRepository extends JpaRepository<Reference,Long> {
    Optional<Reference> findByTitle(String title);
}
