package com.hubgamers.api.repository;

import com.hubgamers.api.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
	
	List<Team> findAllByName(String name);
	
	List<Team> findAllByOrganizerId(String organizerId);
	
	Optional<Team> findByName(String name);
	
	Optional<Team> findByOrganizerId(String organizerId);
}
