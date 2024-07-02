package com.hubgamers.api.controller;

import com.hubgamers.api.model.TeamRoster;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TeamRosterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/team")
public class TeamAdminController {
	
	private final TeamRosterService teamRosterService;
	
	public TeamAdminController(TeamRosterService teamRosterService) {
		this.teamRosterService = teamRosterService;
	}
	
	@GetMapping("/columns")
	public ResponseJson<List<String>> getAdminColumns() {
		return new ResponseJson<>(teamRosterService.getAdminColumns(), HttpStatus.OK.value());
	}
	
	@GetMapping("/owner/{organizerId}")
	public ResponseJson<TeamRoster> getTeamByOwner(@PathVariable String organizerId) {
		return new ResponseJson<>(teamRosterService.getTeamByOwner(organizerId), HttpStatus.OK.value());
	}
}
