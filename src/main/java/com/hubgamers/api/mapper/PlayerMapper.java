package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.dto.PlayerDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class PlayerMapper implements MapperInstance<PlayerDTO, Player>{
	
	@Override
	public PlayerDTO toDTO(Player entity) {
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setId(entity.getId());
		playerDTO.setUsername(entity.getUsername());
		playerDTO.setUserId(entity.getUserId());
		return playerDTO;
	}
	
	@Override
	public List<PlayerDTO> toDTO(List<Player> entityList) {
		List<PlayerDTO> playerDTOList = new ArrayList<>();
		entityList.forEach(e -> {
			playerDTOList.add(toDTO(e));
		});
		return playerDTOList;
	}
	
	@Override
	public Player toEntity(PlayerDTO dto) {
		Player player = new Player();
		player.setId(dto.getId());
		player.setUsername(dto.getUsername());
		player.setUserId(dto.getUserId());
		return player;
	}
	
	@Override
	public List<Player> toEntity(List<PlayerDTO> dtoList) {
		List<Player> playerList = new ArrayList<>();
		dtoList.forEach(d -> {
			playerList.add(toEntity(d));
		});
		return playerList;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprietes = new ArrayList<>();
		Field[] champs = Player.class.getDeclaredFields();
		for (Field champ : champs) {
			proprietes.add(champ.getName());
		}
		return proprietes;
	}
}
