package com.hubgamers.api.service;

import com.hubgamers.api.mapper.InvitationMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Team;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.repository.InvitationRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService {
    
    private final InvitationRepository invitationRepository;
    
    private final TeamService teamService;
    
    private final PlayerService playerService;
    
    private final TeamMapper teamMapper = new TeamMapper();
    
    private final InvitationMapper invitationMapper = new InvitationMapper();
    
    public InvitationService(InvitationRepository invitationRepository, TeamService teamService, PlayerService playerService) {
        this.invitationRepository = invitationRepository;
		this.teamService = teamService;
		this.playerService = playerService;
	}

    public List<String> getColumns() {
        return invitationMapper.getColumns();
    }
    
    public List<Invitation> getAll() {
//        return invitationRepository.findAll();
        return new ArrayList<>();
        
    }
    
    public List<Invitation> getAllByTeamId(String teamId) {
        return invitationRepository.findAllByTeamId(teamId);
    }
    
    public List<Invitation> getAllByPlayerId(String playerId) {
        return invitationRepository.findAllByPlayerId(playerId);
    }
    
    /*
    * Récupérer toutes les invitation à rejoindre cette équipe d'autres joueurs
     */
    public List<Invitation> getAllJoinInvitationByTeamId(String teamId) {
        return invitationRepository.findAllByTeamIdAndType(teamId, Invitation.InvitationType.JOIN_TEAM);
    }
    
    /*
    * Récupérer toutes les invitation de recrutement de joueurs pour cette équipe
     */
    public List<Invitation> getAllRecruitPlayerInvitationByTeamId(String teamId) {
        return invitationRepository.findAllByTeamIdAndType(teamId, Invitation.InvitationType.RECRUIT_PLAYER);
    }
    
    public Invitation getInvitationById(String id) {
        return invitationRepository.findById(id).orElse(null);
    }

    public Invitation createInvitation(InvitationDTO invitationDTO) {
        Invitation invitation = invitationMapper.toEntity(invitationDTO);
        Team team = teamService.getTeamById(invitation.getTeamId());
        invitation.setTitle("Invitation de '" + team.getName() + "' à rejoindre l'équipe");
        return invitationRepository.save(invitation);
    }
    
    public Invitation acceptInvitation(String id) throws AccountNotFoundException {
        Invitation invitation = invitationRepository.findById(id).orElse(null);
        if (invitation == null) {
            throw new RuntimeException("Invitation not found");
        }
        invitation.setStatus(Invitation.InvitationStatus.ACCEPTED);
        invitation = invitationRepository.save(invitation);
        
        Team team = teamService.getTeamById(invitation.getTeamId());
        Player player = playerService.getPlayerById(invitation.getPlayerId());
        team.getPlayers().add(player);
        teamService.updateTeam(teamMapper.toDTO(team));
        return invitation;
    }
    
    public Invitation declineInvitation(String id) {
        Invitation invitation = invitationRepository.findById(id).orElse(null);
        if (invitation == null) {
            throw new RuntimeException("Invitation not found");
        }
        invitation.setStatus(Invitation.InvitationStatus.REFUSED);
        return invitationRepository.save(invitation);
    }
    
    public void deleteInvitation(String id) {
        invitationRepository.deleteById(id);
    }
}
