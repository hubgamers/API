package com.hubgamers.api.service;

import com.hubgamers.api.mapper.InvitationMapper;
import com.hubgamers.api.mapper.TeamMapper;
import com.hubgamers.api.mapper.TeamRosterMapper;
import com.hubgamers.api.model.*;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService {
    
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRosterService teamRosterService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;
    
    private final TeamMapper teamMapper = new TeamMapper();
    
    private final TeamRosterMapper teamRosterMapper = new TeamRosterMapper();
    
    private final InvitationMapper invitationMapper = new InvitationMapper();

    public List<String> getColumns() {
        return invitationMapper.getColumns();
    }
    
    public List<Invitation> getAll() {
//        return invitationRepository.findAll();
        return new ArrayList<>();
        
    }
    
    public List<Invitation> getAllByTeamId(Long teamId) {
        return invitationRepository.findAllByTeamId(teamId);
    }
    
    public List<Invitation> getAllByPlayerId(Long playerId) {
        return invitationRepository.findAllByPlayerId(playerId);
    }
    
    public List<Invitation> getAllInvitationsByTeamIdAndType(Long teamId, Invitation.InvitationType type) {
        return invitationRepository.findAllByTeamIdAndType(teamId, type);
    }
    
    public Invitation getInvitationById(String id) {
        return invitationRepository.findById(id).orElse(null);
    }

    public Invitation createInvitation(InvitationDTO invitationDTO) throws AccountNotFoundException {
        Invitation invitation = invitationMapper.toEntity(invitationDTO);
        if (invitation.getType().equals(Invitation.InvitationType.RECRUIT_STAFF)) {
            // Invitation de recrutement dans le staff
            Team team = teamService.getTeamById(invitation.getTeamId());
            invitation.setTitle("Invitation de '" + team.getName() + "' à rejoindre le staff");
        } else if (invitation.getType().equals(Invitation.InvitationType.RECRUIT_PLAYER)) {
            // Invitation de recrutement de joueur
            TeamRoster teamRoster = teamRosterService.getTeamRosterById(invitation.getTeamId());
            invitation.setTitle("Invitation de '" + teamRoster.getName() + "' à rejoindre l'équipe");
        } else if (invitation.getType().equals(Invitation.InvitationType.JOIN_STAFF)) {
            // Invitation de rejoindre l'équipe (staff)
            invitation.setTitle("Invitation de '" + userService.getUserConnected().getUsername() + "' à rejoindre le staff");
        } else if (invitation.getType().equals(Invitation.InvitationType.JOIN_TEAM_ROSTER)) {
            // Invitation de rejoindre l'équipe (joueur)
            Player player = playerService.getPlayerById(invitation.getPlayerId());
            invitation.setTitle("Invitation de '" + player.getUsername() + "' à rejoindre l'équipe");
        }
        return invitationRepository.save(invitation);
    }
    
    public Invitation acceptInvitation(String id) throws AccountNotFoundException {
        Invitation invitation = invitationRepository.findById(id).orElse(null);
        if (invitation == null) {
            throw new RuntimeException("Invitation not found");
        }
        invitation.setStatus(Invitation.InvitationStatus.ACCEPTED);
        invitation = invitationRepository.save(invitation);
        
        if (invitation.getType().equals(Invitation.InvitationType.JOIN_STAFF)) {
            Team team = teamService.getTeamById(invitation.getTeamId());
            User user = userService.getUserById(String.valueOf(invitation.getUserId()));
            List<User> users = new ArrayList<User>(List.copyOf(team.getUsers()));
            users.add(user);
            team.setUsers(users);
            teamService.updateTeam(teamMapper.toDTO(team));
        } else if (invitation.getType().equals(Invitation.InvitationType.JOIN_TEAM_ROSTER)) {
            TeamRoster teamRoster = teamRosterService.getTeamRosterById(invitation.getTeamId());
            Player player = playerService.getPlayerById(invitation.getPlayerId());
            teamRoster.getPlayers().add(player);
            teamRosterService.updateTeamRoster(teamRosterMapper.toDTO(teamRoster));
        }
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
