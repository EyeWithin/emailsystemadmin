package com.emailsystemadmin.service;

import java.util.List;
import com.emailsystemadmin.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    String deleteUser(Long id);
    String deleteBulkUsers(List<Long> ids);
}