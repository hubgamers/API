package com.hubgamers.api.service;

import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	
	private final PlayerRepository playerRepository;
	
	private final PlayerMapper playerMapper = new PlayerMapper();
	
	UserService userService;
	
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}
	
	public Player getPlayerById(String id) throws AccountNotFoundException {
		Optional<Player> optionalPlayer = playerRepository.findById(id);
		if (optionalPlayer.isEmpty()) {
			throw new AccountNotFoundException("Player not found");
		}
		return optionalPlayer.get();
	}
	
	public Player getPlayerByUsername(String username) throws AccountNotFoundException {
		return playerRepository.findByUserId(userService.getUserByUsername(username).getId());
	}
	
	public Player createPlayer(PlayerDTO playerDTO) {
		return playerRepository.save(playerMapper.toEntity(playerDTO));
	}
	
	public Player updatePlayer(PlayerDTO playerDTO) {
		return playerRepository.save(playerMapper.toEntity(playerDTO));
	}
	
	public void deletePlayer(String id) throws AccountNotFoundException {
		playerRepository.delete(getPlayerById(id));
	}
}
