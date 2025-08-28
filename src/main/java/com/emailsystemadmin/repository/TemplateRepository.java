package com.emailsystemadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emailsystemadmin.entity.TemplateEntity;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

	TemplateEntity findByTemplateName(String templateName);

}
