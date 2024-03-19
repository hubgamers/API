package com.hubgamers.api.service;

import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	private final TeamMapper teamMapper = new TeamMapper();
	
	UserService userService;
	
	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public List<String> getColumns() {
		return teamMapper.getColumns();
	}
	
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}
	
	public Team getTeamById(String id) {
		return teamRepository.findById(id).orElse(null);
	}
	
	public Team getTeamByName(String name) {
		return teamRepository.findByName(name).orElse(null);
	}
	
	public Team getTeamByOwner(String organizerId) {
		return teamRepository.findByOrganizerId(organizerId).orElse(null);
	}
	
	public Team createTeam(TeamDTO teamDTO) {
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public Team updateTeam(TeamDTO teamDTO) {
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public void deleteTeam(String id) {
		teamRepository.delete(getTeamById(id));
	}

}
