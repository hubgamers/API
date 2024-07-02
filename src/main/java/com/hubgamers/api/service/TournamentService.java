package com.hubgamers.api.service;

import com.cloudinary.utils.ObjectUtils;
import com.hubgamers.api.mapper.TournamentMapper;
import com.hubgamers.api.model.Participant;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Tournament;
import com.hubgamers.api.model.dto.TournamentDTO;
import com.hubgamers.api.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TournamentService {
	
	private final TournamentRepository tournamentRepository;
	
	private final TournamentMapper tournamentMapper = new TournamentMapper();
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService userService;
	
	public TournamentService(TournamentRepository tournamentRepository) {
		this.tournamentRepository = tournamentRepository;
	}

	public List<String> getAdminColumns() {
		return tournamentMapper.getAdminColumns();
	}

	public List<String> getColumns() {
		return tournamentMapper.getColumns();
	}
	
	public List<Tournament> getAllTournaments() {
//		return tournamentRepository.findAll();
		return new ArrayList<>();
		
	}
	
	public List<Tournament> getMyTournaments() {
		return tournamentRepository.findAllByOrganizerId(userService.getUserConnected().getId());
	}
	
	public Tournament getTournamentById(String id) {
		return tournamentRepository.findById(id).orElse(null);
	}
	
	public Tournament createTournament(TournamentDTO tournamentDTO) {
		tournamentDTO.setOrganizerId(userService.getUserConnected().getId());
		return tournamentRepository.save(tournamentMapper.toEntity(tournamentDTO));
	}
	
	public Tournament addParticipant(String id, Participant participant) {
		Tournament tournament = getTournamentById(id);
		if (tournament == null) {
			throw new RuntimeException("Tournament not found");
		}
//		if (participant.getPlayer() == null && participant.getTeamRoster() == null) {
//			throw new RuntimeException("Team or player is required");
//		}
		tournament.getParticipants().add(participant);
		return tournamentRepository.save(tournament);
	}
	
	public Tournament uploadBanner(String id, MultipartFile file) {
		Tournament tournament = getTournamentById(id);
		if (tournament == null) {
			throw new RuntimeException("Tournament not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/banner",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		tournament.setBanner(fileService.addImageCloudinary(file, params).get("url").toString());
		return tournamentRepository.save(tournament);
	}
	
	public Tournament uploadLogo(String id, MultipartFile file) {
		Tournament tournament = getTournamentById(id);
		if (tournament == null) {
			throw new RuntimeException("Tournament not found");
		}
		Map params = ObjectUtils.asMap(
				"folder", "hubgamers/logo",
				"use_filename", false,
				"unique_filename", true,
				"overwrite", true
		);
		tournament.setLogo(fileService.addImageCloudinary(file, params).get("url").toString());
		return tournamentRepository.save(tournament);
	}
	
	public Tournament updateTournament(Tournament tournament) {
		return tournamentRepository.save(tournament);
	}
	
	public void deleteTournament(String id) {
		tournamentRepository.deleteById(id);
	}
}
