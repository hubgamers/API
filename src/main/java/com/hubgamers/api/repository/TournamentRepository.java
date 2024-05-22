package com.hubgamers.api.repository;

import com.hubgamers.api.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends MongoRepository<Tournament, String> {
	
	List<Tournament> findAllByOrganizerId(String organizerId);
}
