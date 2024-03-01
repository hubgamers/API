package com.hubgamers.api.repository;

import com.hubgamers.api.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
