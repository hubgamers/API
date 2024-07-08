package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeamDTO {
	public Long id;
	
	@NotBlank
	public String name;

	@NotBlank
	public Set<Long> tags = Set.of();
	
	public String description;
	
	public boolean visibility;

	public TeamRoster.PaidType paidType = TeamRoster.PaidType.FREE;
	
	public Region region;
	
	public List<PlayerDTO> players = new ArrayList<>();
	
	@NotBlank
	public Long organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
