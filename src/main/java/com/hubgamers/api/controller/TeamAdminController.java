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
@RequestMapping("api/admin/team")
public class TeamAdminController {
	
	private final TeamService teamService;
	
	public TeamAdminController(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getAdminColumns() {
		return new ResponseJson<>(teamService.getAdminColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<Team> getTeamByOwner(@PathVariable String organizerId) {
		return new ResponseJson<>(teamService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
}
