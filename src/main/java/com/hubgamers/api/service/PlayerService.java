package com.hubgamers.api.service;

import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	
	private final PlayerRepository playerRepository;
	
	private final PlayerMapper playerMapper = new PlayerMapper();
	
	@Autowired
	private UserService userService;
	
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public List<String> getAdminColumns() {
		return playerMapper.getAdminColumns();
	}

	public List<String> getColumns() {
		return playerMapper.getColumns();
	}
	
	public List<Player> getAllPlayers() {
		return playerRepository.findAllByVisibility(true);
	}
	
	public Player getPlayerById(String id) throws AccountNotFoundException {
		Optional<Player> optionalPlayer = playerRepository.findById(id);
		if (optionalPlayer.isEmpty()) {
			throw new AccountNotFoundException("Player not found");
		}
		return optionalPlayer.get();
	}
	
	public Player getPlayerByUserId(Long userId) throws AccountNotFoundException {
		Optional<Player> optionalPlayer = Optional.ofNullable(playerRepository.findByUserId(userId));
		if (optionalPlayer.isEmpty()) {
			throw new AccountNotFoundException("Player not found");
		}
		return optionalPlayer.get();
	}
	
	public Player getPlayerByUsername(String username) {
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
