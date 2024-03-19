package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class TeamMapper implements com.hubgamers.api.mapper.Mapper<Team, TeamDTO> {

	@Override
	public Class<Team> getEntityClass() {
		return Team.class;
	}

	@Override
	public Class<TeamDTO> getDTOClass() {
		return TeamDTO.class;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Team.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}
}
