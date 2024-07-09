package com.hubgamers.api.service;

import com.hubgamers.api.model.Game;
import com.hubgamers.api.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

	private final GameRepository gameRepository;
	
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	public List<String> getColumns() {
		return Arrays.asList("id", "name");
	}
	
	public Iterable<Game> getAllGames() {
		return gameRepository.findAll();
	}
}
