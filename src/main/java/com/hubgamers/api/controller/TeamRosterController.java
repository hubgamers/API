package com.hubgamers.api.controller;

import com.hubgamers.api.model.TeamRoster;
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
@RequestMapping("api/teamRoster")
public class TeamRosterController {
	
	private final TeamRosterService teamRosterService;
	
	private UserService userService;
	
	public TeamRosterController(TeamRosterService teamRosterService, UserService userService) {
		this.teamRosterService = teamRosterService;
		this.userService = userService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(teamRosterService.getColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all-public")
	public ResponseJson<List<TeamRoster>> getAllPublicTeams() {
		return new ResponseJson<>(teamRosterService.getAllPublicTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all/name/{name}")
	public ResponseJson<List<TeamRoster>> getAllTeamNames(@PathVariable String name) {
		return new ResponseJson<>(teamRosterService.getAllTeamNames(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/my-teams")
	public ResponseJson<List<TeamRoster>> getMyTeams() {
		return new ResponseJson<>(teamRosterService.getMyTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/name/{name}")
	public ResponseJson<TeamRoster> getTeamByName(@PathVariable String name) {
		return new ResponseJson<>(teamRosterService.getTeamByName(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/id/{id}")
	public ResponseJson<TeamRoster> getTeamById(@PathVariable String id) {
		return new ResponseJson<>(teamRosterService.getTeamById(id), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<TeamRoster> getTeamByOwner(@PathVariable String organizerId) {
		return new ResponseJson<>(teamRosterService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<TeamRoster> createTeam(@RequestBody TeamDTO teamDTO) {
		return new ResponseJson<>(teamRosterService.createTeam(teamDTO), HttpStatus.CREATED.value());
	}
	
	@PostMapping("/banner/upload/{id}")
	public ResponseJson<TeamRoster> uploadBanner(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamRosterService.uploadBanner(id, file), HttpStatus.OK.value());
	}
	
	@PostMapping("/logo/upload/{id}")
	public ResponseJson<TeamRoster> uploadLogo(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamRosterService.uploadLogo(id, file), HttpStatus.OK.value());
	}
	
	@PutMapping("/update")
	public ResponseJson<TeamRoster> updateTeam(@RequestBody TeamDTO teamDTO) throws AccountNotFoundException {
		return new ResponseJson<>(teamRosterService.updateTeam(teamDTO), HttpStatus.OK.value());
	}
	
	@PostMapping("/delete/{id}")
	public void deleteTeam(@PathVariable String id) {
		teamRosterService.deleteTeam(id);
	}
}
