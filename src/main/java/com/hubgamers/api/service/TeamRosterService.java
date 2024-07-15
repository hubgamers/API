package com.hubgamers.api.service;

import com.hubgamers.api.mapper.TeamRosterMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.model.dto.TeamRosterDTO;
import com.hubgamers.api.repository.TeamRosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class TeamRosterService {
	
	private final TeamRosterRepository teamRosterRepository;
	
	private final TeamRosterMapper teamRosterMapper = new TeamRosterMapper();

	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;

	public TeamRosterService(TeamRosterRepository teamRosterRepository) {
		this.teamRosterRepository = teamRosterRepository;
	}
	
	public List<String> getAdminColumns() {
		return teamRosterMapper.getAdminColumns();
	}

	public List<String> getColumns() {
		return teamRosterMapper.getColumns();
	}
	
	public List<TeamRoster> getAllPublicTeamRosters() {
		return teamRosterRepository.findAllByVisibility(true);
	}
	
	public List<TeamRoster> getAllTeamRosterNames(String name) {
		return teamRosterRepository.findAllByNameLike(name);
	}
	
	public List<TeamRoster> getTeamRostersByPlayerId(Long playerId) {
		return teamRosterRepository.findByPlayersId(playerId);
	}
	
	public List<TeamRoster> getMyTeamRosters() {
		Long organizerId = userService.getUserConnected().getId();
		return teamRosterRepository.findAllByOrganizerId(organizerId);
	}
	
	public List<TeamRoster> getTeamRostersByTeamId(Long teamId) {
		return teamRosterRepository.findAllByTeamId(teamId);
	}
	
	public TeamRoster getTeamRosterById(Long id) {
		return teamRosterRepository.findById(id).orElse(null);
	}
	
	public TeamRoster getTeamRosterByName(String name) {
		return teamRosterRepository.findByName(name).orElse(null);
	}
	
	public TeamRoster getTeamRosterByOwner(Long organizerId) {
		return teamRosterRepository.findByOrganizerId(organizerId).orElse(null);
	}
	
	public TeamRoster createTeamRoster(TeamRosterDTO teamRosterDTO) {
		teamRosterDTO.setOrganizerId(userService.getUserConnected().getId());
		return teamRosterRepository.save(teamRosterMapper.toEntity(teamRosterDTO));
	}
	
	public TeamRoster updateTeamRoster(TeamRosterDTO teamRosterDTO) {
		return teamRosterRepository.save(teamRosterMapper.toEntity(teamRosterDTO));
	}
	
	public void deleteTeamRoster(Long id) {
		teamRosterRepository.deleteById(id);
	}
}
