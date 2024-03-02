package com.hubgamers.api.service;

import com.hubgamers.api.mapper.TournamentMapper;
import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.model.User;
import com.hubgamers.api.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
	
	private final TournamentRepository tournamentRepository;
	
	private final TournamentMapper tournamentMapper = new TournamentMapper();
	
	UserService userService;
	
	public TournamentService(TournamentRepository tournamentRepository) {
		this.tournamentRepository = tournamentRepository;
	}
	
	public List<Tournament> getAllTournaments() {
		return tournamentRepository.findAll();
	}
	
	public Tournament getTournamentById(String id) {
		return tournamentRepository.findById(id).orElse(null);
	}
	
	public Tournament createTournament(Tournament tournament) {
		return tournamentRepository.save(tournament);
	}
	
	public Tournament updateTournament(Tournament tournament) {
		return tournamentRepository.save(tournament);
	}
	
	public void deleteTournament(String id) {
		tournamentRepository.deleteById(id);
	}
}
