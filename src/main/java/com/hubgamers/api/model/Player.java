package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	public String username;
	
	@Column(name = "user_id")
	public Long userId;
	
	@Column(name = "avatar")
	public String avatar;

	@Column(name = "visibility")
	public boolean visibility = true;
	
	/**
	 * Un participant de tournoi soit une équipe ou un joueur
	 */
	@Data
	@NoArgsConstructor
	public static class Participant {
		
		public TeamRoster teamRoster;
		
		public Player player;
	}
}
