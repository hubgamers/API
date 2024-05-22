package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "tournaments")
public class Tournament {
	@Id
	public String id;
	
	public String name;
	
	public TournamentType type;
	
	public LocalDateTime startDate;
	
	public LocalDateTime endDate;
	
	public List<Player.Participant> participants = new ArrayList<>();
	
	public String description;
	
	public String rules;
	
	public String game;
	
	public String platform;
	
	public Region region;
	
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
