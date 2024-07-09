package com.hubgamers.api.repository;

import com.hubgamers.api.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {
	List<Player> findAllByVisibility(boolean isPublic);
	
	List<Player> findAllByVisibilityAndUsernameContaining(boolean isPublic, String name);
	
	Player findByUserId(Long userId);
}
