package com.hubgamers.api.controller;

import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tournament")
public class TournamentController {
    
    private final TournamentService tournamentService;
    
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
    
    @GetMapping("/columns")
    public ResponseJson<List<String>> getColumns() {
        return new ResponseJson<>(tournamentService.getColumns(), HttpStatus.OK.value());
    }
    
    @GetMapping("/all")
    public ResponseJson<List<Tournament>> getAllTournaments() {
        return new ResponseJson<>(tournamentService.getAllTournaments(), HttpStatus.OK.value());
    }
    
    @GetMapping("/id/{id}")
    public ResponseJson<Tournament> getTournamentById(@PathVariable String id) {
        return new ResponseJson<>(tournamentService.getTournamentById(id), HttpStatus.OK.value());
    }
    
    @PostMapping("/create")
    public ResponseJson<Tournament> createTournament(@RequestBody Tournament tournament) {
        return new ResponseJson<>(tournamentService.createTournament(tournament), HttpStatus.CREATED.value());
    }
    
    @PutMapping("/update")
    public ResponseJson<Tournament> updateTournament(@RequestBody Tournament tournament) {
        return new ResponseJson<>(tournamentService.updateTournament(tournament), HttpStatus.OK.value());
    }
    
    @PostMapping("/delete/{id}")
    public void deleteTournament(@PathVariable String id) {
        tournamentService.deleteTournament(id);
    }
}
