package com.hubgamers.api.fixtures;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Role;
import com.hubgamers.api.model.User;
import com.hubgamers.api.repository.PlayerRepository;
import com.hubgamers.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataFixtures {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final PlayerRepository playerRepository;
	
	@Value("${app.admin.username}")
	private String adminUsername;
	
	@Value("${app.admin.email}")
	private String adminEmail;
	
	@Value("${app.admin.password}")
	private String adminPassword;
	
	@Autowired
	public UserDataFixtures(UserRepository userRepository, PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.playerRepository = playerRepository;
	}
	
	public void createTestData() {
		User admin = new User();
		admin.setUsername(adminUsername);
		admin.setEmail(adminEmail);
		admin.setPassword(passwordEncoder.encode(adminPassword)); // Correction ici
		admin.setRoles(List.of(Role.ADMIN, Role.USER, Role.PLAYER));
		userRepository.save(admin);
		
		Player player = new Player();
		player.setUsername(admin.getUsername());
		player.setUserId(admin.getId());
		player.setVisibility(true);
		playerRepository.save(player);
		
		User user2 = new User();
		user2.setUsername("JaneSmith");
		user2.setEmail("jane.smith@example.com");
		user2.setPassword(passwordEncoder.encode("password"));
		user2.setRoles(List.of(Role.USER, Role.PLAYER));
		userRepository.save(user2);
		
		Player player2 = new Player();
		player2.setUsername(user2.getUsername());
		player2.setUserId(user2.getId());
		player2.setVisibility(true);
		playerRepository.save(player2);
		
		User user3 = new User();
		user3.setUsername("JohnDoe");
		user3.setEmail("john.doe@example.com");
		user3.setPassword(passwordEncoder.encode("password"));
		user2.setRoles(List.of(Role.USER, Role.PLAYER));
		userRepository.save(user3);
		
		Player player3 = new Player();
		player3.setUsername(user3.getUsername());
		player3.setUserId(user3.getId());
		player3.setVisibility(true);
		playerRepository.save(player3);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
		playerRepository.deleteAll();
	}
}
