package com.hubgamers.api.fixtures;

import com.hubgamers.api.model.Game;
import com.hubgamers.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GameDataFixtures {
	
	private final GameRepository gameRepository;

	@Autowired
	public GameDataFixtures(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	public void createData() {
		Set<String> names = Set.of("Valorant", "Rocket League", "Rainbow Six Siege", "League of Legends", "XDefiant", "Call of Duty", "Fortnite");
		if (gameRepository.count() == 0) {
			for (String name : names) {
				Game game = new Game();
				game.setName(name);
				gameRepository.save(game);
			}
		}
	}
}
