package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Un participant de tournoi soit une équipe ou un joueur
 */
@Data
@NoArgsConstructor
public class ParticipantDTO {
    
    public Team team;
    
    public Player player;
}
