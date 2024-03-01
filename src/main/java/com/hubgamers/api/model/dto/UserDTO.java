package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
	public String id;
	
	public String username;
	
	public String password;
	
	public String email;
	
	public List<Role> roles;
}
