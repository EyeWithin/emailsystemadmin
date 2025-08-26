package com.emailsystemadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emailsystemadmin.dto.UserDTO;
import com.emailsystemadmin.dto.UserResponseMsgDTO;
import com.emailsystemadmin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseMsgDTO> createUser(@RequestBody UserDTO userDto) {
    	UserResponseMsgDTO response = userService.createUser(userDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<UserResponseMsgDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    
    @DeleteMapping("/deleteBulkByIds")
    public ResponseEntity<String> deleteBulkUsers(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(userService.deleteBulkUsers(ids));
    }
    
    @GetMapping("/test")
    public String testEndpoint() {
        return "User service is running!";
    }
}