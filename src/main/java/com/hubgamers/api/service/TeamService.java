package com.hubgamers.api.service;

import com.cloudinary.utils.ObjectUtils;
import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	private final TeamMapper teamMapper = new TeamMapper();

	@Autowired
	private UserService userService;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private FileService fileService;
	
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
//		return teamRepository.findAll();
		return new ArrayList<>();
		
	}
	
	public List<Team> getAllTeamNames(String name) {
		return teamRepository.findAllByNameLike(name);
	}
	
	public List<Team> getMyTeams(String organizerId) {
		return teamRepository.findAllByOrganizerId(organizerId);
	}
	
	public Team getTeamById(Long id) {
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
		System.out.println("teamDTO.getPlayers() = " + teamDTO);
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}
	
	public Team uploadBanner(Long id, MultipartFile file) {
		Team team = getTeamById(id);
		if (team == null) {
			throw new RuntimeException("Team not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/banner",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		team.setBanner(fileService.addImageCloudinary(file, params).get("url").toString());
		return teamRepository.save(team);
	}
	
	public Team uploadLogo(Long id, MultipartFile file) {
		Team team = getTeamById(id);
		if (team == null) {
			throw new RuntimeException("Team not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/logo",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		team.setLogo(fileService.addImageCloudinary(file, params).get("url").toString());
		return teamRepository.save(team);
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
	
	public void deleteTeam(Long id) {
		teamRepository.delete(getTeamById(id));
	}

}
