package com.hubgamers.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
	public String id;
	
	public String username;
	
	public String userId;
}
