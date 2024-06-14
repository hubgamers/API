package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Role;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
	@Id
	public String id;
	
	@NotNull
	public String username;
	
	/*
	* On n'impose pas de mot de passe pour la création d'un utilisateur en tant qu'admin
	 */
	public String password;
	
	@NotNull
	public String email;
	
	@NotNull
	public List<Role> roles;
	
	public String stripeSessionId;
	
	public String stripePriceId;

	public String stripeSubscriptionId;
}
