package com.hubgamers.api.model;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "games")
public class Game {
	public Long id;
	
	public String name;
}
