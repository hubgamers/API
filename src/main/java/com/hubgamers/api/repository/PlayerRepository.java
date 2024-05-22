package com.hubgamers.api.repository;

import com.hubgamers.api.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
	Player findByUserId(String userId);
}
