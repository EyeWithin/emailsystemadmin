package com.emailsystemadmin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailsystemadmin.dto.AdminDto;
import com.emailsystemadmin.entity.AdminEntity;
import com.emailsystemadmin.repository.AdminRepository;
import com.emailsystemadmin.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminDto createAdmin(AdminDto adminDto) {
		AdminEntity adminEntity = new AdminEntity();
//		BeanUtils.copyProperties(adminDto, adminEntity);
		adminEntity.setUsername(adminDto.getUsername());
		adminEntity.setEmail(adminDto.getEmail());
		adminEntity.setPassword(adminDto.getPassword());
		adminEntity.setRole(adminDto.getRole());
		AdminEntity savedAdmin = adminRepository.save(adminEntity);
		BeanUtils.copyProperties(savedAdmin, adminDto);
		return adminDto;
	}
	
	
	public AdminDto GetAdminById(Long id) {
		AdminEntity adminEntity = adminRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
		AdminDto adminDto = new AdminDto();
		BeanUtils.copyProperties(adminEntity, adminDto);
		return adminDto;
	}

	@Override
	public List<AdminDto> getAllAdmins() {
		List<AdminEntity> adminEntityList = adminRepository.findAll();
		if (adminEntityList != null && !adminEntityList.isEmpty()) {
			 return adminEntityList.stream()
					.map(adminEntity -> {
						AdminDto dto = new AdminDto();
						BeanUtils.copyProperties(adminEntity, dto);
						return dto;
					}).toList();
		}
		return List.of();
	
	}


	@Override
	public boolean loginAdmin(String username, String password) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = adminRepository.findByUsernameAndPassword(username, password);
		if (adminEntity != null) {
			return true;
		}
		return false;
	}
	
	
}