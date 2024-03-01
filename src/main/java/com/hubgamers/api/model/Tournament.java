package com.hubgamers.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

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
