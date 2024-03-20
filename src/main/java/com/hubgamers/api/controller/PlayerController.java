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
@RequestMapping("api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/columns")
    public ResponseJson<List<String>> getColumns() {
        return new ResponseJson<>(playerService.getColumns(), HttpStatus.OK.value());
    }

    @GetMapping("/all")
    public ResponseJson<List<Player>> getAllPlayers() {
        return new ResponseJson<>(playerService.getAllPlayers(), HttpStatus.OK.value());
    }

    @GetMapping("/username/{username}")
    public ResponseJson<Player> getPlayerByUsername(@PathVariable String username) throws AccountNotFoundException {
        return new ResponseJson<>(playerService.getPlayerByUsername(username), HttpStatus.OK.value());
    }

    @GetMapping("/id/{id}")
    public ResponseJson<Player> getPlayerById(@PathVariable String id) throws AccountNotFoundException {
        return new ResponseJson<>(playerService.getPlayerById(id), HttpStatus.OK.value());
    }

    @PostMapping("/create")
    public ResponseJson<Player> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return new ResponseJson<>(playerService.createPlayer(playerDTO), HttpStatus.CREATED.value());
    }

    @PutMapping("/update")
    public ResponseJson<Player> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        return new ResponseJson<>(playerService.updatePlayer(playerDTO), HttpStatus.OK.value());
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(String id) throws AccountNotFoundException {
        playerService.deletePlayer(id);
    }
}
