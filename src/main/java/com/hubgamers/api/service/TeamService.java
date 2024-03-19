package com.hubgamers.api.service;

import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.repository.TeamRepository;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	private final TeamMapper teamMapper = new TeamMapper();

	@Autowired
	private UserService userService;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerMapper playerMapper;
	
	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public List<String> getAdminColumns() {
		return teamMapper.getAdminColumns();
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
		teamDTO.setOrganizerId(userService.getUserConnected().getId());
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public Team updateTeam(TeamDTO teamDTO) throws AccountNotFoundException {
		System.out.println("teamDTO.getPlayers() = " + teamDTO.getPlayers());
		for (PlayerDTO playerDTO : teamDTO.getPlayers()) {
			int index = teamDTO.getPlayers().indexOf(playerDTO);
			if (playerDTO.getId() != null) {
				Player playerDb = playerService.getPlayerById(playerDTO.getId());
				playerDTO = playerMapper.toDTO(playerDb);
				// Remplacer à l'index
				teamDTO.getPlayers().set(index, playerDTO);
				System.out.println("playerDTO = " + playerDTO);
			}
		}
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public void deleteTeam(String id) {
		teamRepository.delete(getTeamById(id));
	}

}
