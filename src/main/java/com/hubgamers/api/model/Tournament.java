package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "tournaments")
public class Tournament {
	public String id;
	
	public String name;
	
	public TournamentType type;
	
	public String description;
	
	public String game;
	
	public String platform;
	
	public Region region;
	
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
