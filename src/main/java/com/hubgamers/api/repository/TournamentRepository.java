package com.hubgamers.api.repository;

import com.hubgamers.api.model.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TournamentRepository extends CrudRepository<Tournament, String> {
	
	List<Tournament> findAllByOrganizerId(String organizerId);
}
