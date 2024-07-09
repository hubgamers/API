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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title")
    public String title;

    @Column(name = "user_id")
    public Long userId;
    
    @Column(name = "player_id")
    public Long playerId;
    
    @Column(name = "team_id")
    public Long teamId;
    
    @Column(name = "roster_id")
    public Long rosterId;
    
    @Column(name = "status")
    public InvitationStatus status = InvitationStatus.PENDING;
    
    @Column(name = "type")
    public InvitationType type;
    
    public enum InvitationStatus {
        PENDING,
        ACCEPTED,
        REFUSED
    }
    
    public enum InvitationType {
        JOIN_STAFF, // Rejoindre l'équipe
        JOIN_TEAM_ROSTER, // Rejoindre le roster de l'équipe
        RECRUIT_STAFF, // Recruter un utilisateur pour la gestion de l'équipe
        RECRUIT_PLAYER // Recruter un joueur pour un roster d'équipe
    }
}
