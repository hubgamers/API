package com.hubgamers.api.repository;

import com.hubgamers.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	// Trouver l'utilisateur par son login (username ou email)
	Optional<User> findByEmailOrUsername(String email, String username);
}
