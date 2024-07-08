package com.hubgamers.api.service;

import com.cloudinary.utils.ObjectUtils;
import com.hubgamers.api.exception.BadRequestException;
import com.hubgamers.api.mapper.UserMapper;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final UserMapper userMapper = new UserMapper();
	
	@Autowired
	private FileService fileService;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<String> getAdminColumns() {
		return userMapper.getAdminColumns();
	}

	public List<String> getColumns() {
		return userMapper.getColumns();
	}

	public User getUserConnected() {
		UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new BadRequestException("User not found");
		}
	}
	
	public List<User> getAllUsers() {
//		return userRepository.findAll();
		return new ArrayList<>();
		
	}
	
	public User getUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new BadRequestException("User not found");
		}
		return user.get();
	}
	
	public User getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new BadRequestException("User not found");
		}
		return user.get();
	}
	
	public User getUserByLogin(String login) {
		Optional<User> user = userRepository.findByEmailOrUsername(login, login);
		if (user.isEmpty()) {
			throw new BadRequestException("User not found");
		}
		return user.get();
	}
	
	public User createUser(UserDTO userDTO) {
		return userRepository.save(userMapper.toEntity(userDTO));
	}
	
	public User updateUser(UserDTO userDTO) {
		return userRepository.save(userMapper.toEntity(userDTO));
	}
	
	public void uploadAvatar(MultipartFile file) {
		User user = getUserConnected();
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/avatar",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		user.setAvatar(fileService.addImageCloudinary(file, params).get("url").toString());
		userRepository.save(user);
	}
	
	public void deleteUser(String id) {
		userRepository.delete(getUserById(id));
	}
}
