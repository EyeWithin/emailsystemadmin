package com.emailsystemadmin.service;

import com.emailsystemadmin.dto.EmailDTO;

public interface EmailService {

	String sendEmail(EmailDTO emailDto);

}
