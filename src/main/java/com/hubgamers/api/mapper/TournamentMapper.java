package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.model.dto.TournamentDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class TournamentMapper implements MapperInstance<TournamentDTO, Tournament> {
	@Override
	public TournamentDTO toDTO(Tournament entity) {
		TournamentDTO tournamentDTO = new TournamentDTO();
		tournamentDTO.setId(entity.getId());
		tournamentDTO.setName(entity.getName());
		tournamentDTO.setGame(entity.getGame());
		tournamentDTO.setLogo(entity.getLogo());
		tournamentDTO.setBanner(entity.getBanner());
		tournamentDTO.setDescription(entity.getDescription());
		tournamentDTO.setOrganizerId(entity.getOrganizerId());
		tournamentDTO.setPlatform(entity.getPlatform());
		tournamentDTO.setRegion(entity.getRegion());
		tournamentDTO.setType(entity.getType());
		tournamentDTO.setSocialMedia(entity.getSocialMedia());
		return tournamentDTO;
	}
	
	@Override
	public List<TournamentDTO> toDTO(List<Tournament> entityList) {
		List<TournamentDTO> tournamentDTOList = null;
		entityList.forEach(e -> {
			tournamentDTOList.add(toDTO(e));
		});
		return tournamentDTOList;
	}
	
	@Override
	public Tournament toEntity(TournamentDTO dto) {
		Tournament tournament = new Tournament();
		tournament.setId(dto.getId());
		tournament.setName(dto.getName());
		tournament.setGame(dto.getGame());
		tournament.setLogo(dto.getLogo());
		tournament.setBanner(dto.getBanner());
		tournament.setDescription(dto.getDescription());
		tournament.setOrganizerId(dto.getOrganizerId());
		tournament.setPlatform(dto.getPlatform());
		tournament.setRegion(dto.getRegion());
		tournament.setType(dto.getType());
		tournament.setSocialMedia(dto.getSocialMedia());
		return tournament;
	}
	
	@Override
	public List<Tournament> toEntity(List<TournamentDTO> dtoList) {
		List<Tournament> tournamentList = new ArrayList<>();
		dtoList.forEach(d -> {
			tournamentList.add(toEntity(d));
		});
		return tournamentList;
	}
}
