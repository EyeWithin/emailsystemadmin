package com.emailsystemadmin.service;

import java.util.List;

import com.emailsystemadmin.dto.EmailDTO;

public interface EmailService {

	String sendEmail(EmailDTO emailDto);

	String sendBulkEmail(List<EmailDTO> emailListDto);

}
