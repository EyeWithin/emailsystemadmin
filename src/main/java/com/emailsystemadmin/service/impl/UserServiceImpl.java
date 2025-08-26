package com.emailsystemadmin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailsystemadmin.dto.UserDTO;
import com.emailsystemadmin.entity.User;
import com.emailsystemadmin.repository.UserRepository;
import com.emailsystemadmin.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, userDto);
        return userDto;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        BeanUtils.copyProperties(userDto, existingUser, "id");
        User updatedUser = userRepository.save(existingUser);
        BeanUtils.copyProperties(updatedUser, userDto);
        return userDto;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDto = new UserDTO();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }

    @Override
    public String deleteBulkUsers(List<Long> ids) {
        List<Long> notFoundIds = ids.stream()
                .filter(id -> !userRepository.existsById(id))
                .collect(Collectors.toList());

        if (!notFoundIds.isEmpty()) {
            throw new RuntimeException("Users not found with ids: " + notFoundIds);
        }

        userRepository.deleteAllById(ids);
        return "Successfully deleted " + ids.size() + " users";
    }
}