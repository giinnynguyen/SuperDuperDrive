package com.ginny.mstodo.repository;

import com.ginny.mstodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer userId);
}
