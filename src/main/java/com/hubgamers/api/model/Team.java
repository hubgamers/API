package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
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
	
	@Column(name = "players")
	@ManyToMany
	@JoinTable(name = "players", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	public List<Player> players = new ArrayList<>();
	
	@Column(name = "organizer_id")
	public String organizerId;
	
	@Column(name = "logo")
	public String logo;
	
	@Column(name = "banner")
	public String banner;
	
	@Column(name = "invitations")
	@ManyToMany
	@JoinTable(name = "invitations", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "invitation_id"))
	public List<Invitation> invitations;
	
	public enum PaidType {
		FREE, PREMIUM
	}
}
