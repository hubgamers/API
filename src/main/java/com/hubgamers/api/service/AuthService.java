package com.hubgamers.api.service;

import com.hubgamers.api.model.AuthRequest;
import com.hubgamers.api.model.AuthResponse;
import com.hubgamers.api.model.Role;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final UserService userService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	JwtService jwtService;
	
	public AuthService(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = new UserService(userRepository);
	}
	
	public User register(AuthRequest authRequest) throws AccountNotFoundException {
		if (userRepository.existsByUsername(authRequest.getUsername())) {
			throw new AccountNotFoundException("Username already exists");
		} else if (userRepository.existsByEmail(authRequest.getEmail())) {
			throw new AccountNotFoundException("Email already exists");
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(authRequest.getUsername());
		userDTO.setEmail(authRequest.getEmail());
		userDTO.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
		userDTO.setRoles(List.of(Role.USER));
		
		return userService.createUser(userDTO);
	}
	
	public AuthResponse authResponse(User user, String username) {
		user.setRefreshToken(jwtService.createRefreshToken());
		userRepository.save(user);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwtToken(jwtService.generateToken(username));
		authResponse.setRefreshToken(user.getRefreshToken());
		authResponse.setUserId(String.valueOf(user.getId()));
		authResponse.setUsername(user.getUsername());
		return authResponse;
	}
}
