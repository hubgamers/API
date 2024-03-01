package com.hubgamers.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "teams")
public class Team {
	public String id;
	
	public String name;
	
	public String description;
	
	public String game;
	
	public String platform;
	
	public Region region;
	
	public List<Player> players;
	
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
