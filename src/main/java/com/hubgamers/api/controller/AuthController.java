package com.hubgamers.api.controller;

import com.hubgamers.api.model.AuthRequest;
import com.hubgamers.api.model.AuthResponse;
import com.hubgamers.api.model.User;
import com.hubgamers.api.repository.UserRepository;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.AuthService;
import com.hubgamers.api.service.JwtService;
import com.hubgamers.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	private System.Logger logger = System.getLogger(AuthController.class.getName());
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseJson<User> register(@RequestBody AuthRequest authRequest) throws AccountNotFoundException {
		logger.log(System.Logger.Level.INFO, "Registering user: " + authRequest.getLogin());
		if (authRequest.getUsername() == null || authRequest.getEmail() == null) {
			throw new AccountNotFoundException("Username or email is required");
		}
		return new ResponseJson<>(authService.register(authRequest), HttpStatus.OK.value());
	}
	
	@PostMapping("/login")
	public ResponseJson<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws AccountNotFoundException, AccessDeniedException {
		logger.log(System.Logger.Level.INFO, "Authenticating user: " + authRequest.getLogin());
		// Trouver l'utilisateur par son login (username ou email)
		User user = userService.getUserByLogin(authRequest.getLogin());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), authRequest.getPassword())
		);
		if (authRequest.getRefreshToken() != null) {
			if (Objects.requireNonNull(user).getRefreshToken().equals(authRequest.getRefreshToken())) {
				throw new AccessDeniedException("Refresh token not found");
			}
		}
		user.setRefreshToken(jwtService.createRefreshToken());
		userRepository.save(user);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwtToken(jwtService.generateToken(user.getUsername()));
		authResponse.setRefreshToken(user.getRefreshToken());
		authResponse.setUserId(user.getId());
		authResponse.setUsername(user.getUsername());
		return new ResponseJson<>(authResponse, HttpStatus.OK.value(), jwtService.getExpiration());
	}
	
	@PostMapping("/refresh")
	public ResponseJson<AuthResponse> refreshToken(@RequestBody AuthRequest authRequest) throws AccessDeniedException {
		logger.log(System.Logger.Level.INFO, "Refreshing token for user: " + authRequest.getUsername());
		if (authRequest.getRefreshToken() == null && authRequest.getLogin() == null) {
			throw new AccessDeniedException("Invalid credentials");
		}
		User user = userService.getUserByLogin(authRequest.getLogin());
		if (!user.getRefreshToken().equals(authRequest.getRefreshToken())) {
			throw new AccessDeniedException("Refresh token invalid");
		}
		return new ResponseJson<>(authService.authResponse(user, authRequest.getLogin()), HttpStatus.OK.value(), jwtService.getExpiration());
	}
}
