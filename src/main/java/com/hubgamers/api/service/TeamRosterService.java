package com.hubgamers.api.service;

import com.cloudinary.utils.ObjectUtils;
import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.repository.TeamRosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeamRosterService {
	
	private final TeamRosterRepository teamRosterRepository;
	
	private final TeamMapper teamMapper = new TeamMapper();

	@Autowired
	private UserService userService;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PlayerMapper playerMapper;
	
	public TeamRosterService(TeamRosterRepository teamRosterRepository) {
		this.teamRosterRepository = teamRosterRepository;
	}
	
	public List<String> getAdminColumns() {
		return teamMapper.getAdminColumns();
	}

	public List<String> getColumns() {
		return teamMapper.getColumns();
	}
	
	public List<TeamRoster> getAllPublicTeams() {
		return teamRosterRepository.findAllByVisibility(true);
	}
	
	public List<TeamRoster> getAllTeamNames(String name) {
		return teamRosterRepository.findAllByNameLike(name);
	}
	
	public List<TeamRoster> getMyTeams() {
		Long organizerId = userService.getUserConnected().getId();
		return teamRosterRepository.findAllByOrganizerId(organizerId);
	}
	
	public TeamRoster getTeamById(Long id) {
//		return teamRosterRepository.findById(id).orElse(null);
		return null;
	}
	
	public TeamRoster getTeamByName(String name) {
		return teamRosterRepository.findByName(name).orElse(null);
	}
	
	public TeamRoster getTeamByOwner(Long organizerId) {
		return teamRosterRepository.findByOrganizerId(organizerId).orElse(null);
	}
	
	public TeamRoster createTeam(TeamDTO teamDTO) {
		teamDTO.setOrganizerId(userService.getUserConnected().getId());
		return teamRosterRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public TeamRoster uploadBanner(Long id, MultipartFile file) {
		TeamRoster teamRoster = getTeamById(id);
		if (teamRoster == null) {
			throw new RuntimeException("Team not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/banner",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		teamRoster.setBanner(fileService.addImageCloudinary(file, params).get("url").toString());
		return teamRosterRepository.save(teamRoster);
	}
	
	public TeamRoster uploadLogo(Long id, MultipartFile file) {
		TeamRoster teamRoster = getTeamById(id);
		if (teamRoster == null) {
			throw new RuntimeException("Team not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/logo",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		teamRoster.setLogo(fileService.addImageCloudinary(file, params).get("url").toString());
		return teamRosterRepository.save(teamRoster);
	}
	
	public TeamRoster updateTeam(TeamDTO teamDTO) throws AccountNotFoundException {
		System.out.println("teamDTO.getPlayers() = " + teamDTO.getPlayers());
		for (PlayerDTO playerDTO : teamDTO.getPlayers()) {
			int index = teamDTO.getPlayers().indexOf(playerDTO);
			if (playerDTO.getId() != null) {
				Player playerDb = playerService.getPlayerById(String.valueOf(playerDTO.getId()));
				playerDTO = playerMapper.toDTO(playerDb);
				// Remplacer à l'index
				teamDTO.getPlayers().set(index, playerDTO);
				System.out.println("playerDTO = " + playerDTO);
			}
		}
		return teamRosterRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public void deleteTeam(Long id) {
		teamRosterRepository.delete(getTeamById(id));
	}

}
