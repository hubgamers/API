package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamRosterDTO {
	private Long id;
	
	private String name;
	
	private String tag;
	
	private String description;
	
	private boolean visibility;
	
	private String game;
	
	private String platform;
	
	private Long organizerId;
	
	private Long teamId;
	
	private String logo;
	
	private String banner;

	public List<Player> players;
}
