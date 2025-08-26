package com.emailsystemadmin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.emailsystemadmin.dto.AdminDTO;
import com.emailsystemadmin.dto.AdminResponseMsgDTO;
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
	public AdminResponseMsgDTO createAdmin(AdminDTO adminDto) {
		AdminResponseMsgDTO response = new AdminResponseMsgDTO();

		AdminEntity adminEntityByUsername = adminRepository.findByUsername(adminDto.getUsername());
		if (adminEntityByUsername != null) {
			response.setMessage("Username already exists with " + adminDto.getUsername());
			response.setStatus(false);
			return response;

			}
			AdminEntity adminEntity = new AdminEntity();
//			BeanUtils.copyProperties(adminDto, adminEntity);
			adminEntity.setUsername(adminDto.getUsername());
			adminEntity.setEmail(adminDto.getEmail());
			adminEntity.setPassword(adminDto.getPassword());
			adminEntity.setRole(adminDto.getRole());
			AdminEntity savedAdmin = adminRepository.save(adminEntity);
//			BeanUtils.copyProperties(savedAdmin, adminDto);
			
			response.setMessage("Admin created successfully");
			response.setId(savedAdmin.getId());
			response.setStatus(true);
			
			
		return response;
	}
	
	

	@Override
	public AdminResponseMsgDTO updateAdmin(Long id, AdminDTO adminDto) {
		AdminResponseMsgDTO response = new AdminResponseMsgDTO();
			
		Optional<AdminEntity> adminEntityOptional = adminRepository.findById(id);
		if (!adminEntityOptional.isPresent()) {
			response.setMessage("Admin not found with id: " + id);
			response.setStatus(false);
			return response;
		}
			AdminEntity adminEntity = adminEntityOptional.get();
			BeanUtils.copyProperties(adminDto, adminEntity, "id");
			AdminEntity updatedAdmin = adminRepository.save(adminEntity);
	//		BeanUtils.copyProperties(updatedAdmin, adminDto);
			response.setMessage("Admin updated successfully");
			response.setId(updatedAdmin.getId());
			response.setStatus(true);
			return response;
	}
		
	
	@Override
	public AdminDTO GetAdminById(Long id) {
		AdminEntity adminEntity = adminRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found with id: " + id));
		AdminDTO adminDto = new AdminDTO();
		BeanUtils.copyProperties(adminEntity, adminDto);
		return adminDto;
	}

	
	@Override
	public String deleteAdmin(Long id) {
		Optional<AdminEntity> adminEntity = adminRepository.findById(id);
		if (adminEntity.isPresent()) {
			adminRepository.deleteById(id);
			return "Admin deleted successfully!";
		}
		return "Admin not found with id: " + id;
	}
	
	@Override
	public List<AdminDTO> getAllAdmins() {
		List<AdminEntity> adminEntityList = adminRepository.findAll();
		if (adminEntityList != null && !adminEntityList.isEmpty()) {
			 return adminEntityList.stream()
					.map(adminEntity -> {
						AdminDTO dto = new AdminDTO();
						BeanUtils.copyProperties(adminEntity, dto);
						return dto;
					}).toList();
		}
		return List.of();
	
	}


	@Override
	public AdminResponseMsgDTO loginAdmin(String username, String password) {
		AdminResponseMsgDTO response = new AdminResponseMsgDTO();
		
		AdminEntity adminEntity = adminRepository.findByUsernameAndPassword(username, password);
		if (adminEntity != null) {
			response.setId(adminEntity.getId());
			response.setMessage("Login successful");
			response.setStatus(true);
			return response;
		}
		response.setMessage("Invalid username or password");
		response.setStatus(false);
		return response;
	}

	
	
}