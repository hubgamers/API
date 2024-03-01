package com.hubgamers.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {
	
	public String id;
	
	public String username;
	
	public String password;
	
	public String email;
	
	public List<Role> roles;
}
