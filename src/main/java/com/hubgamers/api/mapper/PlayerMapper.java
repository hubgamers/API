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
public class PlayerMapper implements com.hubgamers.api.mapper.Mapper<Player, PlayerDTO> {

	private final List<String> IGNORE_FIELDS = List.of("id", "userId");

	@Override
	public Class<Player> getEntityClass() {
		return Player.class;
	}

	@Override
	public Class<PlayerDTO> getDTOClass() {
		return PlayerDTO.class;
	}

	@Override
	public List<String> getAdminColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Player.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Player.class.getDeclaredFields();
		for (Field champ : champs) {
			if (!IGNORE_FIELDS.contains(champ.getName())) {
				proprieties.add(champ.getName());
			}
		}
		return proprieties;
	}
}
