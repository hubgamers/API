package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.model.dto.TournamentDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class TournamentMapper implements com.hubgamers.api.mapper.Mapper<Tournament, TournamentDTO> {

	@Override
	public Class<Tournament> getEntityClass() {
		return Tournament.class;
	}

	@Override
	public Class<TournamentDTO> getDTOClass() {
		return TournamentDTO.class;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Tournament.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}
}
