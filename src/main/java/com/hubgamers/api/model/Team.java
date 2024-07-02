package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	public String name;

	@Column(name = "tag")
	public String tag;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "visibility")
	public boolean visibility = true;
	
	@Column(name = "paid_type")
	public PaidType paidType;
	
	@Column(name = "game")
	public String game;
	
	@Column(name = "platform")
	public String platform;
	
	@ManyToMany
	@JoinTable(
			name = "team_players",
			joinColumns = @JoinColumn(name = "team_id", nullable = true),
			inverseJoinColumns = @JoinColumn(name = "player_id", nullable = true)
	)
	public List<Player> players;
	
	@Column(name = "organizer_id")
	public Long organizerId;
	
	@Column(name = "logo")
	public String logo;
	
	@Column(name = "banner")
	public String banner;
	
	@ManyToMany
	public List<Invitation> invitations;
	
	public enum PaidType {
		FREE, PREMIUM
	}
}
