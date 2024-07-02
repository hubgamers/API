package com.hubgamers.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team_players")
public class TeamPlayers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name = "team_id")
	public Integer teamId;
	
	@Column(name = "player_id")
	public Integer playerId;
}
