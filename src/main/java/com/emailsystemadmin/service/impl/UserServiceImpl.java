package com.emailsystemadmin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailsystemadmin.dto.UserDTO;
import com.emailsystemadmin.dto.UserResponseMsgDTO;
import com.emailsystemadmin.entity.UserEntity;
import com.emailsystemadmin.repository.UserRepository;
import com.emailsystemadmin.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

   
	
	
	
	@Override
    public UserResponseMsgDTO createUser(UserDTO userDto) {
    	UserResponseMsgDTO userResponse = new UserResponseMsgDTO();
    	
    	UserEntity userByUsername = userRepository.findByUsername(userDto.getUsername());
	    	if (userByUsername != null) {
	    			userResponse.setMessage("User already exists with ID: " + userDto.getUsername());
	    			userResponse.setStatus(false);
	    			return userResponse;
	    					
	    	}    	
	        UserEntity user = new UserEntity();
	        BeanUtils.copyProperties(userDto, user);
	        UserEntity userSave = userRepository.save(user);
	        
	        userResponse.setId(userSave.getId());
	        userResponse.setMessage("User created successfully");
	        userResponse.setStatus(true);
//	        BeanUtils.copyProperties(savedUser, userDto);
	   return userResponse;
    }
    
    

    @Override
    public UserResponseMsgDTO updateUser(Long id, UserDTO userDto) {
    	UserResponseMsgDTO userResponse = new UserResponseMsgDTO();
    	
        Optional<UserEntity> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isEmpty()) {
        	userResponse.setMessage("User not found with ID: " + id);
			userResponse.setStatus(false);
			return userResponse;
        }

        UserEntity existingUser = existingUserOptional.get();
        BeanUtils.copyProperties(userDto, existingUser, "id");
        UserEntity updatedUser = userRepository.save(existingUser);
//        BeanUtils.copyProperties(updatedUser, userDto);
        
        userResponse.setId(updatedUser.getId());
        userResponse.setMessage("User updated successfully");
        userResponse.setStatus(true);
        return userResponse;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
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
    	Optional<UserEntity> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return "User not found with id: " + id;
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
//            throw new RuntimeException("Users not found with ids: " + notFoundIds);
			return "Users not found with ids: " + notFoundIds;
        }

        userRepository.deleteAllById(ids);
        return "Successfully deleted " + ids.size() + " users";
    }
}