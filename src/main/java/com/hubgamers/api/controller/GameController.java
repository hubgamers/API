package com.hubgamers.api.controller;

import com.hubgamers.api.model.Game;
import com.hubgamers.api.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {
	
	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping("/columns")
	public List<String> getColumns() {
		return gameService.getColumns();
	}
	
	@GetMapping("/all")
	public List<Game> getAllGames() {
		return gameService.getAllGames();
	}
}
