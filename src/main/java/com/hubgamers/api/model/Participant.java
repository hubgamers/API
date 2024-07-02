package com.hubgamers.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Un participant de tournoi soit une équipe ou un joueur
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "participants")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;
	
	@JoinColumn(name = "team")
	@ManyToOne
	public Team team;
	
	@JoinColumn(name = "player")
	@ManyToOne
	public Player player;
}
