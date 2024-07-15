package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	public TeamRoster.PaidType paidType = TeamRoster.PaidType.FREE;
	
	public List<UserDTO> users;
	
	@NotBlank
	public Long organizerId;
	
	public String logo;
	
	public String banner;
}
