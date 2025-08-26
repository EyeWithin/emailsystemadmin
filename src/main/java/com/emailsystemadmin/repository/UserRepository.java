package com.emailsystemadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emailsystemadmin.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    
	UserEntity findByUsername(String username);
}