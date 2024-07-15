package com.hubgamers.api.service;

import com.cloudinary.utils.ObjectUtils;
import com.hubgamers.api.mapper.PlayerMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Tag;
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
	private TagService tagService;

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
	
	public Long countAllTeams() {
		return teamRepository.count();
	}
	
	public List<Team> getAllPublicTeams() {
		return teamRepository.findAllByVisibility(true);
	}
	
	public List<Team> getAllTeamNames(String name) {
		return teamRepository.findAllByNameLike(name);
	}
	
	public List<Team> getMyTeams() {
		List<Team> teamsOwned = teamRepository.findAllByOrganizerId(userService.getUserConnected().getId());
		List<Team> teamsJoined = teamRepository.findByUsersId(userService.getUserConnected().getId());
		return new ArrayList<>(teamsOwned) {{
			addAll(teamsJoined);
		}};
	}
	
	public Team getTeamById(Long id) {
		return teamRepository.findById(id).orElse(null);
	}
	
	public Team getTeamByName(String name) {
		return teamRepository.findByName(name).orElse(null);
	}
	
	public Team getTeamByOwner(Long ownerId) {
		return teamRepository.findByOrganizerId(ownerId).orElse(null);
	}
	
	public Team createTeam(TeamDTO teamDTO) {
		teamDTO.setOrganizerId(userService.getUserConnected().getId());
		Team team = teamMapper.toEntity(teamDTO);
		List<Tag> tags = new ArrayList<>();
		for (Long tagId : teamDTO.getTags()) {
			tags.add(tagService.findTagById(tagId));
		}
		team.setTags(tags);
		return teamRepository.save(team);
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
		Team team = getTeamById(teamDTO.getId());
		if (team == null) {
			throw new RuntimeException("Team not found");
		}
		return teamRepository.save(teamMapper.toEntity(teamDTO));
	}

	public void deleteTeam(Long id) {
		teamRepository.delete(getTeamById(id));
	}
}
