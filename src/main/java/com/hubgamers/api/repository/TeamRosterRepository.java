package com.hubgamers.api.repository;

import com.hubgamers.api.model.TeamRoster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRosterRepository extends MongoRepository<TeamRoster, String> {
	
	@Query("{'name': {$regex: ?0, $options: 'i'}}")
	List<TeamRoster> findAllByNameLike(String name);
	
	List<TeamRoster> findAllByOrganizerId(String organizerId);
	
	Optional<TeamRoster> findByName(String name);
	
	Optional<TeamRoster> findByOrganizerId(String organizerId);
}
