package com.study.blog.business.user.repository;

import com.study.blog.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByEmail(String email);
}
