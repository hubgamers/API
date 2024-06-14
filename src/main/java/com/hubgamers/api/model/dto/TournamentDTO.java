package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TournamentDTO {
	public String id;
	
	@NotBlank
	public String name;
	
	@NotBlank
	public TournamentType type;

	@NotBlank
	public LocalDateTime startDate;

	@NotBlank
	public LocalDateTime endDate;

	public List<Participant> participants = new ArrayList<>();
	
	public String description;

	public String rules;
	
	@NotBlank
	public String game;
	
	@NotBlank
	public String platform;
	
	@NotBlank
	public Region region;
	
	@NotBlank
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
}
