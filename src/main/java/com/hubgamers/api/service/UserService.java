package com.hubgamers.api.service;

import com.hubgamers.api.mapper.UserMapper;
import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final UserMapper userMapper = new UserMapper();
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<String> getColumns() {
		return userMapper.getColumns();
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return user.get();
	}
	
	public User getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return user.get();
	}
	
	public User getUserByLogin(String login) {
		Optional<User> user = userRepository.findByEmailOrUsername(login, login);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return user.get();
	}
	
	public User createUser(UserDTO userDTO) {
		return userRepository.save(userMapper.toEntity(userDTO));
	}
	
	public User updateUser(UserDTO userDTO) {
		return userRepository.save(userMapper.toEntity(userDTO));
	}
	
	public void deleteUser(String id) {
		userRepository.delete(getUserById(id));
	}
}
