package com.emailsystemadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsystemadmin.dto.AdminDTO;
import com.emailsystemadmin.dto.AdminResponseMsgDTO;
import com.emailsystemadmin.service.AdminService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/create")
	public ResponseEntity<AdminResponseMsgDTO> createAdmin(@RequestBody AdminDTO adminDto) {
		AdminResponseMsgDTO createdAdmin = adminService.createAdmin(adminDto);
		return ResponseEntity.ok(createdAdmin);
		
	}
	
	
	   @PutMapping("updateById/{id}")
	    public ResponseEntity<AdminResponseMsgDTO> updateAdmin(@PathVariable Long id,  @RequestBody AdminDTO adminDto) {
		   AdminResponseMsgDTO updatedAdmin = adminService.updateAdmin(id, adminDto);
	        return ResponseEntity.ok(updatedAdmin);
	    }
	
	   
	   @GetMapping("fetchById/{id}")
	    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
	        AdminDTO adminDto = adminService.GetAdminById(id);
	        return ResponseEntity.ok(adminDto);
	    }
	
	
	   @PostMapping("/deleteById/{id}")
	   public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {       
		   return ResponseEntity.ok(adminService.deleteAdmin(id));
	   }
	
	
	@GetMapping("/login/{username}/{password}")
     public ResponseEntity<AdminResponseMsgDTO> loginAdmin(@PathVariable String username, @PathVariable String password) {
		AdminResponseMsgDTO isAuthenticated = adminService.loginAdmin(username, password);
		 return ResponseEntity.ok(isAuthenticated);
	 }
	

	 @GetMapping("/allAdmins")
	 public ResponseEntity<List<AdminDTO>> getAllAdmins() {
	     return ResponseEntity.ok(adminService.getAllAdmins());
	 }
	 @GetMapping("/test")
	 public String testEndpoint() {
	     return "Admin service is running!";
	 }

}
