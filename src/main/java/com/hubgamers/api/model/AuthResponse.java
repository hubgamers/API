package com.hubgamers.api.model;

import lombok.Data;

@Data
public class AuthResponse {
	public String jwtToken;
	
	public String refreshToken;
	
	public String userId;
	
	public String username;
}