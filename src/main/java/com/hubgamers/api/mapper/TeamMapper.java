package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class TeamMapper implements MapperInstance<TeamDTO, Team>{
	@Override
	public TeamDTO toDTO(Team entity) {
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setId(entity.getId());
		teamDTO.setName(entity.getName());
		teamDTO.setPlayers(entity.getPlayers());
		return teamDTO;
	}
	
	@Override
	public List<TeamDTO> toDTO(List<Team> entityList) {
		List<TeamDTO> teamDTOList = new ArrayList<>();
		entityList.forEach(e -> {
			teamDTOList.add(toDTO(e));
		});
		return teamDTOList;
	}
	
	@Override
	public Team toEntity(TeamDTO dto) {
		Team team = new Team();
		team.setId(dto.getId());
		team.setName(dto.getName());
		team.setPlayers(dto.getPlayers());
		return team;
	}
	
	@Override
	public List<Team> toEntity(List<TeamDTO> dtoList) {
		List<Team> teamList = new ArrayList<>();
		dtoList.forEach(d -> {
			teamList.add(toEntity(d));
		});
		return teamList;
	}
}
