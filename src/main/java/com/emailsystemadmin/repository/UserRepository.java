package com.emailsystemadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emailsystemadmin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}