package com.emailsystemadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailsystemadmin.dto.TemplateDTO;
import com.emailsystemadmin.entity.TemplateEntity;
import com.emailsystemadmin.repository.TemplateRepository;
import com.emailsystemadmin.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private TemplateRepository templateRepository;
	
	
	
	
	
	

	@Override
	public String createTemplate(TemplateDTO templateDTO) {
		TemplateEntity templateEntity = templateRepository.findByTemplateName(templateDTO.getTemplateName());
		if (templateEntity != null) {
			return "Template with the same name already exists.";
		}
		templateEntity = new TemplateEntity();
		templateEntity.setTemplateName(templateDTO.getTemplateName());
		templateEntity.setDescription(templateDTO.getDescription());
		templateEntity.setTemplateBody(templateDTO.getTemplateBody());
		templateRepository.save(templateEntity);	
		return "Template created successfully.";	
	}



	@Override
	public Object getAllTemplates() {
		List<TemplateEntity> templateEntity = templateRepository.findAll();
		return templateEntity;

	}



	@Override
	public String getAllTemplateById(String name) {
		TemplateEntity templateEntity = templateRepository.findByTemplateName(name);
		if (templateEntity == null) {
			return "Template not found.";
		}
		return templateEntity.toString();
	}

}
