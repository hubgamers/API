package com.hubgamers.api.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	private String logo;
	
	private String banner;
}
