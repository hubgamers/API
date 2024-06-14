package com.hubgamers.api.repository;

import com.hubgamers.api.model.Invitation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitationRepository extends CrudRepository<Invitation, String> {
    
    List<Invitation> findAllByTeamId(String teamId);
    
    List<Invitation> findAllByPlayerId(String playerId);
    
    List<Invitation> findAllByTeamIdAndType(String teamId, Invitation.InvitationType type);
}
