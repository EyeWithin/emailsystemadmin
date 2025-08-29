package com.emailsystemadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateNameList {
	private String templateNameList;

	public String getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(String templateNameList) {
		this.templateNameList = templateNameList;
	}



}
