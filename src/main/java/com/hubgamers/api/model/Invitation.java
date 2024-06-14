package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "invitations")
public class Invitation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column(name = "title")
    public String title;
    
    @Column(name = "player_id")
    public String playerId;
    
    @Column(name = "team_id")
    public Long teamId;
    
    @Column(name = "status")
    public InvitationStatus status;
    
    @Column(name = "type")
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
