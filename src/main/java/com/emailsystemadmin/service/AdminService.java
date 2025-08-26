package com.emailsystemadmin.service;

import java.util.List;

import com.emailsystemadmin.dto.AdminDTO;
import com.emailsystemadmin.dto.AdminResponseMsgDTO;

public interface AdminService {
	List<AdminDTO> getAllAdmins();

	AdminResponseMsgDTO createAdmin(AdminDTO adminDto);

	AdminResponseMsgDTO loginAdmin(String username, String password);

	AdminResponseMsgDTO updateAdmin(Long id, AdminDTO adminDto);

	AdminDTO GetAdminById(Long id);

	String deleteAdmin(Long id);


}
