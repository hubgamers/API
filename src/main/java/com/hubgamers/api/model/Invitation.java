package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Invitation {
    
    @Id
    public String id;
    
    public String title;
    
    public String playerId;
    
    public String teamId;
    
    public InvitationStatus status;
    
    public InvitationType type;
    
    public enum InvitationStatus {
        PENDING,
        ACCEPTED,
        REFUSED
    }
    
    public enum InvitationType {
        JOIN_TEAM,
        RECRUIT_PLAYER
    }
}
