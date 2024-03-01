package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Region;
import com.hubgamers.api.model.SocialMedia;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDTO {
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
