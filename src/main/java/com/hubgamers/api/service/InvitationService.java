package com.hubgamers.api.service;

import com.hubgamers.api.mapper.InvitationMapper;
import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.repository.InvitationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {
    
    private final InvitationRepository invitationRepository;
    
    private final InvitationMapper invitationMapper = new InvitationMapper();
    
    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public List<String> getColumns() {
        return invitationMapper.getColumns();
    }
    
    public List<Invitation> getAll() {
        return invitationRepository.findAll();
    }
    
    public List<Invitation> getAllByTeamId(String teamId) {
        return invitationRepository.findAllByTeamId(teamId);
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
        return invitationRepository.save(invitationMapper.toEntity(invitationDTO));
    }
    
    public void deleteInvitation(String id) {
        invitationRepository.deleteById(id);
    }
}
