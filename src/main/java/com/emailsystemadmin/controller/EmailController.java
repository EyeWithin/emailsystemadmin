package com.emailsystemadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsystemadmin.dto.EmailDTO;
import com.emailsystemadmin.service.EmailService;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody EmailDTO emailDto) {
		return emailService.sendEmail(emailDto);
		
	}
	
	@PostMapping("/sendBulkEmail")
	public String sendBulkEmail(@RequestBody List<EmailDTO> emailListDto) {
		return emailService.sendBulkEmail(emailListDto);
		
	}
	
	
	
	
	
	
	@GetMapping("/test" )
	public String test() {
		return "Email Service is up and running";
		
	}
	

}
