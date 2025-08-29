package com.emailsystemadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsystemadmin.dto.TemplateDTO;
import com.emailsystemadmin.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	
	@GetMapping("/test")
	public String testTemplate() {
		return "Template Controller is working!";
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<String> createTemplate(@RequestBody TemplateDTO templateDTO) {
		return ResponseEntity.ok(templateService.createTemplate(templateDTO));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTemplates() {
		return ResponseEntity.ok(templateService.getAllTemplates());
	}

	@GetMapping("/getNameList")
	public ResponseEntity<?> getTemplatesNameList() {
		return ResponseEntity.ok(templateService.getTemplatesNameList());
	}

	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<String> getTemplateById(@PathVariable String name) {
		String getTemplateById = templateService.getAllTemplateById(name);
		return ResponseEntity.ok(getTemplateById);
		
	}


	
	
	
	
	
	
	
}
