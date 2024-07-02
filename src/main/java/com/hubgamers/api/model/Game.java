package com.hubgamers.api.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "games")
public class Game {
	
	public String id;
	
	public String name;
}
