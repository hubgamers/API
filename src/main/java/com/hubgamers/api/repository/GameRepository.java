package com.hubgamers.api.repository;

import com.hubgamers.api.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
