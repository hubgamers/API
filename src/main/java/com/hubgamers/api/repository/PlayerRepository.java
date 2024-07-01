package com.hubgamers.api.repository;

import com.hubgamers.api.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, String> {
	Player findByUserId(Long userId);
}
