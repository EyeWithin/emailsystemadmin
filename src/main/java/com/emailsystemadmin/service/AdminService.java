package com.emailsystemadmin.service;

import java.util.List;

import com.emailsystemadmin.dto.AdminDto;

public interface AdminService {
	List<AdminDto> getAllAdmins();

	AdminDto createAdmin(AdminDto adminDto);

	boolean loginAdmin(String username, String password);


}
