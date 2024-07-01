package com.hubgamers.api.repository;

import com.hubgamers.api.model.Invitation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitationRepository extends CrudRepository<Invitation, String> {
    
    List<Invitation> findAllByTeamId(Long teamId);
    
    List<Invitation> findAllByPlayerId(Long playerId);
    
    List<Invitation> findAllByTeamIdAndType(Long teamId, Invitation.InvitationType type);
}
