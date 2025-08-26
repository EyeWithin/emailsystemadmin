package com.emailsystemadmin.service;

import java.util.List;
import com.emailsystemadmin.dto.UserDTO;
import com.emailsystemadmin.dto.UserResponseMsgDTO;

public interface UserService {
	UserResponseMsgDTO createUser(UserDTO userDto);
	UserResponseMsgDTO updateUser(Long id, UserDTO userDto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    String deleteUser(Long id);
    String deleteBulkUsers(List<Long> ids);
}