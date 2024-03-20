package com.hubgamers.api.controller;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.dto.PlayerDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/admin/player")
public class PlayerAdminController {
	
	private final PlayerService playerService;
	
	public PlayerAdminController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/columns")
	public ResponseJson<List<String>> getAdminColumns() {
		return new ResponseJson<>(playerService.getAdminColumns(), HttpStatus.OK.value());
	}
}
