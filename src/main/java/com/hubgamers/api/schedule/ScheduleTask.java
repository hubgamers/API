package com.hubgamers.api.schedule;

import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.service.PlayerService;
import com.hubgamers.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ScheduleTask {

	private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerMapper playerMapper;
	
	@Autowired
	private UserService userService;

	// Tous les 5 minutes
	@Scheduled(fixedRate = 300000)
	public void syncUserAvatarWithPlayer() {
		log.info("syncUserAvatarWithPlayer - The time is now {}", dateFormat.format(System.currentTimeMillis()));
		List<Player> players = playerService.getAllPlayers();
		for (Player player : players) {
			User user = userService.getUserByUsername(player.getUsername());
			if (player.getAvatar() == null) {
				player.setAvatar(user.getAvatar());
				playerService.updatePlayer(playerMapper.toDTO(player));
				log.info("syncUserAvatarWithPlayer - Player {} avatar updated", player.getUsername());
			} else if (!player.getAvatar().equals(user.getAvatar())) {
				PlayerDTO playerDTO = playerMapper.toDTO(player);
				playerDTO.setAvatar(user.getAvatar());
				playerService.updatePlayer(playerDTO);
				log.info("syncUserAvatarWithPlayer - Player {} avatar updated", player.getUsername());
			}
		}
	}
}
