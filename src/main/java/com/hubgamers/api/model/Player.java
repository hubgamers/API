package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@Document(collection = "players")
public class Player {
	@Id
	public String id;
	
	@Indexed(unique = true)
	public String username;
	
	public String userId;
	
	/**
	 * Un participant de tournoi soit une équipe ou un joueur
	 */
	@Data
	@NoArgsConstructor
	public static class Participant {
		
		public Team team;
		
		public Player player;
	}
}
