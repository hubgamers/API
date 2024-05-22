package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Player;
import com.hubgamers.api.model.Region;
import com.hubgamers.api.model.SocialMedia;
import com.hubgamers.api.model.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDTO {
	public String id;
	
	@NotBlank
	public String name;
	
	public String description;
	
	public Team.PaidType paidType = Team.PaidType.FREE;
	
	@NotBlank
	public String game;
	
	@NotBlank
	public String platform;
	
//	@NotBlank
	public Region region;
	
	public List<PlayerDTO> players;
	
	@NotBlank
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
