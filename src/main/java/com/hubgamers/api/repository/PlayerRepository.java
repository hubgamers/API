package com.hubgamers.api.repository;

import com.hubgamers.api.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
	Player findByUserId(String userId);
}
