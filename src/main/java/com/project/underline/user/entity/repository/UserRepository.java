package com.project.underline.user.entity.repository;

import com.project.underline.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //로그인 시
    Optional<User> findByEmail(String email);

    //중복 가입 방지
    boolean existsByEmail(String email);

    User findByNickname(String userNickname);
}
