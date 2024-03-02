package com.hubgamers.api.repository;

import com.hubgamers.api.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {
	Optional<Team> findByName(String name);
	
	Optional<Team> findByOrganizerId(String organizerId);
}
