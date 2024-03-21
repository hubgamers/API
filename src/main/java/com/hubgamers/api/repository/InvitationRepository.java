package com.hubgamers.api.repository;

import com.hubgamers.api.model.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends MongoRepository<Invitation, String> {
    
    List<Invitation> findAllByTeamId(String teamId);
    
    List<Invitation> findAllByTeamIdAndType(String teamId, Invitation.InvitationType type);
}
