package com.emailsystemadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emailsystemadmin.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

	AdminEntity findByUsernameAndPassword(String username, String password);

	AdminEntity findByUsername(String username);

	AdminEntity findById(long id);
}
