package com.hubgamers.api.controller;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.TeamDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(teamService.getColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all")
	public ResponseJson<List<Team>> getAllTeams() {
		return new ResponseJson<>(teamService.getAllTeams(), HttpStatus.OK.value());
	}
	
	@GetMapping("/name/{name}")
	public ResponseJson<Team> getTeamByName(@PathVariable String name) {
		return new ResponseJson<>(teamService.getTeamByName(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/id/{id}")
	public ResponseJson<Team> getTeamById(@PathVariable String id) {
		return new ResponseJson<>(teamService.getTeamById(id), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<Team> getTeamByOwner(@PathVariable String organizerId) {
		return new ResponseJson<>(teamService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<Team> createTeam(@RequestBody TeamDTO teamDTO) {
		return new ResponseJson<>(teamService.createTeam(teamDTO), HttpStatus.CREATED.value());
	}
	
	@PutMapping("/update")
	public ResponseJson<Team> updateTeam(@RequestBody TeamDTO teamDTO) throws AccountNotFoundException {
		return new ResponseJson<>(teamService.updateTeam(teamDTO), HttpStatus.OK.value());
	}
	
	@PostMapping("/delete/{id}")
	public void deleteTeam(@PathVariable String id) {
		teamService.deleteTeam(id);
	}
}
