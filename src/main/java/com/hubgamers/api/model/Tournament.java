package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tournaments")
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "type")
	public TournamentType type;
	
	@Column(name = "start_date")
	public LocalDateTime startDate;
	
	@Column(name = "end_date")
	public LocalDateTime endDate;
	
	@Column(name = "participants")
	@ManyToMany
	@JoinTable(name = "participants", joinColumns = @JoinColumn(name = "tournament_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))
	public List<Participant> participants = new ArrayList<>();
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "rules")
	public String rules;
	
	@Column(name = "game")
	public String game;
	
	@Column(name = "platform")
	public String platform;
	
	@Column(name = "organizer_id")
	public String organizerId;
	
	@Column(name = "logo")
	public String logo;
	
	@Column(name = "banner")
	public String banner;
}
