package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.model.dto.TeamRosterDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class TeamRosterMapper implements com.hubgamers.api.mapper.Mapper<TeamRoster, TeamRosterDTO> {

	private final List<String> IGNORE_FIELDS = List.of("id", "organizerId", "description", "socialMedia", "banner", "logo");

	@Override
	public Class<TeamRoster> getEntityClass() {
		return TeamRoster.class;
	}

	@Override
	public Class<TeamRosterDTO> getDTOClass() {
		return TeamRosterDTO.class;
	}

	@Override
	public List<String> getAdminColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = TeamRoster.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = TeamRoster.class.getDeclaredFields();
		for (Field champ : champs) {
			if (!IGNORE_FIELDS.contains(champ.getName())) {
				proprieties.add(champ.getName());
			}
		}
		return proprieties;
	}
}

