package com.emailsystemadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsystemadmin.dto.AdminDto;
import com.emailsystemadmin.service.AdminService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/users/admin")
@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/create")
	public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
		AdminDto createdAdmin = adminService.createAdmin(adminDto);
		return ResponseEntity.ok(createdAdmin);
		
	}
	
	@GetMapping("/login/{username}/{password}")
     public ResponseEntity<Boolean> loginAdmin(@PathVariable String username, @PathVariable String password) {
		 boolean isAuthenticated = adminService.loginAdmin(username, password);
		 return ResponseEntity.ok(isAuthenticated);
	 }
	
	
	
		// This class can be used to define admin-specific endpoints
	// For example, you might want to add methods for managing user roles, permissions, etc.

	 @GetMapping("/allAdmins")
	 public ResponseEntity<List<AdminDto>> getAllAdmins() {
	     return ResponseEntity.ok(adminService.getAllAdmins());
	 }
	 @GetMapping("/test")
	 public String testEndpoint() {
	     return "Admin service is running!";
	 }

}
