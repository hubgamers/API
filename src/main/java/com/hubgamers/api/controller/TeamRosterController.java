package com.hubgamers.api.controller;

import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.model.dto.TeamRosterDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TeamRosterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/teamRoster")
public class TeamRosterController {
	
	private final TeamRosterService teamRosterService;
	
	public TeamRosterController(TeamRosterService teamRosterService) {
		this.teamRosterService = teamRosterService;}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getColumns() {
		return new ResponseJson<>(teamRosterService.getColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all-public")
	public ResponseJson<List<TeamRoster>> getAllPublicTeams() {
		return new ResponseJson<>(teamRosterService.getAllPublicTeamRosters(), HttpStatus.OK.value());
	}
	
	@GetMapping("/all/name/{name}")
	public ResponseJson<List<TeamRoster>> getAllTeamNames(@PathVariable String name) {
		return new ResponseJson<>(teamRosterService.getAllTeamRosterNames(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/player/{playerId}")
	public ResponseJson<List<TeamRoster>> getTeamRostersByPlayerId(@PathVariable Long playerId) {
		return new ResponseJson<>(teamRosterService.getTeamRostersByPlayerId(playerId), HttpStatus.OK.value());
	}

	/**
	 * Liste tous les rosters de toutes mes équipes dans le dashboard
	 * @return
	 */
	@GetMapping("/my-teams")
	public ResponseJson<List<TeamRoster>> getMyTeams() {
		return new ResponseJson<>(teamRosterService.getMyTeamRosters(), HttpStatus.OK.value());
	}

	/**
	 * Liste tous les rosters de l'équipe sélectionnée
	 * @param name
	 * @return
	 */
	@GetMapping("/team/{teamId}")
	public ResponseJson<List<TeamRoster>> getTeamRostersByTeamId(@PathVariable Long teamId) {
		return new ResponseJson<>(teamRosterService.getTeamRostersByTeamId(teamId), HttpStatus.OK.value());
	}
	
	@GetMapping("/name/{name}")
	public ResponseJson<TeamRoster> getTeamByName(@PathVariable String name) {
		return new ResponseJson<>(teamRosterService.getTeamRosterByName(name), HttpStatus.OK.value());
	}
	
	@GetMapping("/id/{id}")
	public ResponseJson<TeamRoster> getTeamById(@PathVariable Long id) {
		return new ResponseJson<>(teamRosterService.getTeamRosterById(id), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<TeamRoster> getTeamByOwner(@PathVariable Long organizerId) {
		return new ResponseJson<>(teamRosterService.getTeamRosterByOwner(organizerId), HttpStatus.OK.value());
	}
	
	@PostMapping("/create")
	public ResponseJson<TeamRoster> createTeam(@RequestBody TeamRosterDTO teamRosterDTO) {
		return new ResponseJson<>(teamRosterService.createTeamRoster(teamRosterDTO), HttpStatus.CREATED.value());
	}
	
	@PutMapping("/update")
	public ResponseJson<TeamRoster> updateTeam(@RequestBody TeamRosterDTO teamRosterDTO) throws AccountNotFoundException {
		return new ResponseJson<>(teamRosterService.updateTeamRoster(teamRosterDTO), HttpStatus.OK.value());
	}
	
	@PostMapping("/delete/{id}")
	public void deleteTeam(@PathVariable Long id) {
		teamRosterService.deleteTeamRoster(id);
	}
}
