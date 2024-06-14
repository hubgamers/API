package com.hubgamers.api.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDTO {
	@Id
	public String id;
	
	@NotBlank
	public String username;
	
	@NotBlank
	public String userId;
}
