package com.hubgamers.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDTO {
	public String id;
	
//	@NotBlank
	public String username;
	
//	@NotBlank
	public String userId;
}
