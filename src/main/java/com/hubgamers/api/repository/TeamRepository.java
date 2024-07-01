package com.hubgamers.api.repository;

import com.hubgamers.api.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
//	@Query("{'name': {$regex: ?0, $options: 'i'}}")
	List<Team> findAllByNameLike(String name);
	
	List<Team> findAllByOrganizerId(Long organizerId);
	
	Optional<Team> findByName(String name);
	
	Optional<Team> findByOrganizerId(Long organizerId);
}
