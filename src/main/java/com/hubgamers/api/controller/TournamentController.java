package com.hubgamers.api.controller;

import com.hubgamers.api.model.Participant;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.model.dto.TournamentDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    
    @GetMapping("/count-all")
    public ResponseJson<Long> countAllTournaments() {
        return new ResponseJson<>(tournamentService.countAllTournaments(), HttpStatus.OK.value());
    }
    
    @GetMapping("/all")
    public ResponseJson<List<Tournament>> getAllTournaments() {
        return new ResponseJson<>(tournamentService.getAllTournaments(), HttpStatus.OK.value());
    }
    
    @GetMapping("/my-tournaments")
    public ResponseJson<List<Tournament>> getMyTournaments() {
        return new ResponseJson<>(tournamentService.getMyTournaments(), HttpStatus.OK.value());
    }
    
    @GetMapping("/id/{id}")
    public ResponseJson<Tournament> getTournamentById(@PathVariable String id) {
        return new ResponseJson<>(tournamentService.getTournamentById(id), HttpStatus.OK.value());
    }
    
    @PostMapping("/addParticipant/{id}")
    public ResponseJson<Tournament> addParticipant(@PathVariable String id, @RequestBody Participant participant) {
        return new ResponseJson<>(tournamentService.addParticipant(id, participant), HttpStatus.OK.value());
    }
    
    @PostMapping("/create")
    public ResponseJson<Tournament> createTournament(@RequestBody TournamentDTO tournamentDTO) {
        return new ResponseJson<>(tournamentService.createTournament(tournamentDTO), HttpStatus.CREATED.value());
    }
    
    @PostMapping("/banner/upload/{id}")
    public ResponseJson<Tournament> uploadBanner(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        return new ResponseJson<>(tournamentService.uploadBanner(id, file), HttpStatus.OK.value());
    }
    
    @PostMapping("/logo/upload/{id}")
    public ResponseJson<Tournament> uploadLogo(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        return new ResponseJson<>(tournamentService.uploadLogo(id, file), HttpStatus.OK.value());
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
