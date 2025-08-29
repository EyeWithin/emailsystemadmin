package com.emailsystemadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.emailsystemadmin.dto.EmailDTO;
import com.emailsystemadmin.entity.TemplateEntity;
import com.emailsystemadmin.repository.TemplateRepository;
import com.emailsystemadmin.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	TemplateRepository templateRepository;
	
	
	
	
	@Override
	public String sendEmail(EmailDTO emailDto) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			TemplateEntity templateContent = templateRepository.findByTemplateName(emailDto.getTemplateName());	
			String templateBody = templateContent.getTemplateBody();
			templateBody = templateBody.replace("${name}", emailDto.getUsername());
			
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(emailDto.getEmailId());
			helper.setText(templateBody, true); // true indicates HTML content
			helper.setSubject(templateContent.getDescription());
			
			mailSender.send(message);
//			System.out.println("Email sent to: " + emailDto.getEmailId());
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		return "Email sent successfully"+ " to " + emailDto.getEmailId();
	}




	@Override
	public String sendBulkEmail(List<EmailDTO> emailListDto) {
		TemplateEntity templateContent = templateRepository.findByTemplateName(emailListDto.get(0).getTemplateName());	
		String templateBody = templateContent.getTemplateBody();
		
		for(EmailDTO emailDto : emailListDto) {
			MimeMessage message = mailSender.createMimeMessage();
			try {
				templateBody = templateBody.replace("${name}", emailDto.getUsername());
				
				
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setTo(emailDto.getEmailId());
				helper.setText(templateBody, true); // true indicates HTML content
				helper.setSubject(templateContent.getDescription());
				
				mailSender.send(message);
				System.out.println("Email sent to: " + emailDto.getEmailId());
				
				} catch (MessagingException e) {
				e.printStackTrace();
				}			
		}
		return "Bulk email Sent successfully to " + emailListDto.size() + " users";
	
	}
	
//	@Override
//	public String sendEmail(EmailDTO emailDto) {
//			SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(emailDto.getEmailId());
//		message.setSubject("Email from " + emailDto.getUsername());
//		message.setText("This is a template email: " + emailDto.getTemplateName());
//		
//		 mailSender.send(message);
//		System.out.println("Email sent to: " + emailDto.getEmailId());
//		return "Email sent successfully to " + emailDto.getEmailId();
//	
//	}
	
	
	
	
	

}
