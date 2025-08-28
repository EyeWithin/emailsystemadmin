package com.emailsystemadmin.service;

import com.emailsystemadmin.dto.TemplateDTO;

public interface TemplateService {

	String createTemplate(TemplateDTO templateDTO);

	Object getAllTemplates();
	String getAllTemplateById(String name);

}
