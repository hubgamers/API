package com.hubgamers.api.controller;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TeamRosterService;
import com.hubgamers.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {
	
	private final TeamRosterService teamService;
	
	private UserService userService;
	
	public TeamController(TeamRosterService teamService, UserService userService) {
		this.teamService = teamService;
		this.userService = userService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(teamService.getColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all-public")
	public ResponseJson<List<TeamRoster>> getAllPublicTeams() {
		return new ResponseJson<>(teamService.getAllPublicTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all/name/{name}")
	public ResponseJson<List<TeamRoster>> getAllTeamNames(@PathVariable String name) {
		return new ResponseJson<>(teamService.getAllTeamNames(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/my-teams")
	public ResponseJson<List<TeamRoster>> getMyTeams() {
		return new ResponseJson<>(teamService.getMyTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/name/{name}")
	public ResponseJson<TeamRoster> getTeamByName(@PathVariable String name) {
		return new ResponseJson<>(teamService.getTeamByName(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/id/{id}")
	public ResponseJson<TeamRoster> getTeamById(@PathVariable Long id) {
		return new ResponseJson<>(teamService.getTeamById(id), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<TeamRoster> getTeamByOwner(@PathVariable Long organizerId) {
		return new ResponseJson<>(teamService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<TeamRoster> createTeam(@RequestBody TeamDTO teamDTO) {
		return new ResponseJson<>(teamService.createTeam(teamDTO), HttpStatus.CREATED.value());
	}
	
	@PostMapping("/banner/upload/{id}")
	public ResponseJson<TeamRoster> uploadBanner(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamService.uploadBanner(id, file), HttpStatus.OK.value());
	}
	
	@PostMapping("/logo/upload/{id}")
	public ResponseJson<TeamRoster> uploadLogo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamService.uploadLogo(id, file), HttpStatus.OK.value());
	}
	
	@PutMapping("/update")
	public ResponseJson<TeamRoster> updateTeam(@RequestBody TeamDTO teamDTO) throws AccountNotFoundException {
		return new ResponseJson<>(teamService.updateTeam(teamDTO), HttpStatus.OK.value());
	}
	
	@PostMapping("/delete/{id}")
	public void deleteTeam(@PathVariable Long id) {
		teamService.deleteTeam(id);
	}
}
