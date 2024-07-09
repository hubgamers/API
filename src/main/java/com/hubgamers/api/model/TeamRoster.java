package com.hubgamers.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team_rosters")
public class TeamRoster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	public Long id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "visibility")
	public boolean visibility;
	
	@Column(name = "game")
	public String game;
	
	@Column(name = "platform")
	public String platform;

	@ManyToMany
	@JoinTable(
			name = "team_roster_players",
			joinColumns = @JoinColumn(name = "team_roster_id", nullable = true),
			inverseJoinColumns = @JoinColumn(name = "player_id", nullable = true)
	)
	public List<Player> players;
	
	@Column(name = "organizer_id")
	public Long organizerId;
	
	@Column(name = "team_id")
	public Long teamId;
	
	@Column(name = "logo")
	public String logo;
	
	@Column(name = "banner")
	public String banner;
	
//	public List<Invitation> invitations;
	
	public enum PaidType {
		FREE, PREMIUM
	}
}
