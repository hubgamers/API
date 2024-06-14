package com.hubgamers.api.controller;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TeamService;
import com.hubgamers.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	private UserService userService;
	
	public TeamController(TeamService teamService, UserService userService) {
		this.teamService = teamService;
		this.userService = userService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(teamService.getColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all")
	public ResponseJson<List<Team>> getAllTeams() {
		return new ResponseJson<>(teamService.getAllTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all/name/{name}")
	public ResponseJson<List<Team>> getAllTeamNames(@PathVariable String name) {
		return new ResponseJson<>(teamService.getAllTeamNames(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/my-teams")
	public ResponseJson<List<Team>> getMyTeams() {
		return new ResponseJson<>(teamService.getMyTeams(String.valueOf(userService.getUserConnected().getId())), HttpStatus.OK.value());
	}
	
	@GetMapping("/name/{name}")
	public ResponseJson<Team> getTeamByName(@PathVariable String name) {
		return new ResponseJson<>(teamService.getTeamByName(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/id/{id}")
	public ResponseJson<Team> getTeamById(@PathVariable String id) {
		return new ResponseJson<>(teamService.getTeamById(Long.valueOf(id)), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<Team> getTeamByOwner(@PathVariable String organizerId) {
		return new ResponseJson<>(teamService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<Team> createTeam(@RequestBody TeamDTO teamDTO) {
		return new ResponseJson<>(teamService.createTeam(teamDTO), HttpStatus.CREATED.value());
	}
	
	@PostMapping("/banner/upload/{id}")
	public ResponseJson<Team> uploadBanner(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamService.uploadBanner(Long.valueOf(id), file), HttpStatus.OK.value());
	}
	
	@PostMapping("/logo/upload/{id}")
	public ResponseJson<Team> uploadLogo(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		return new ResponseJson<>(teamService.uploadLogo(Long.valueOf(id), file), HttpStatus.OK.value());
	}
	
	@PutMapping("/update")
	public ResponseJson<Team> updateTeam(@RequestBody TeamDTO teamDTO) throws AccountNotFoundException {
		return new ResponseJson<>(teamService.updateTeam(teamDTO), HttpStatus.OK.value());
	}
	
	@PostMapping("/delete/{id}")
	public void deleteTeam(@PathVariable String id) {
		teamService.deleteTeam(Long.valueOf(id));
	}
}
