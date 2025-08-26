package com.emailsystemadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseMsgDTO {
	private Long id;
	private String message;
	private boolean status;

}
