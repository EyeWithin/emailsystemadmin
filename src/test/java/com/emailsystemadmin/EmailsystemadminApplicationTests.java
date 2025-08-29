package com.emailsystemadmin;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class EmailsystemadminApplicationTests {

	@Disabled
	@Test
	void contextLoads() {
	}

	@MockBean
    private JavaMailSender mailSender; // mock the missing bean
}
