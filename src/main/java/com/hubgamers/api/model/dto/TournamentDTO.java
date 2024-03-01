package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Region;
import com.hubgamers.api.model.SocialMedia;
import com.hubgamers.api.model.TournamentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TournamentDTO {
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
