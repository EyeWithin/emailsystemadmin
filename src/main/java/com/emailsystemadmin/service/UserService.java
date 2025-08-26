package com.emailsystemadmin.service;

import java.util.List;
import com.emailsystemadmin.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO updateUser(Long id, UserDTO userDto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    String deleteUser(Long id);
    String deleteBulkUsers(List<Long> ids);
}