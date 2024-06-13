package com.hubgamers.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Id
	@GeneratedValue(generator = "uuid")
	public String id;
	
	@NotNull
	public String username;
	
	@JsonIgnore
	public String password;
	
	@JsonIgnore
	public String refreshToken;
	
	@Indexed(unique = true)
	@NotNull
	public String email;
	
	public List<Role> roles;
	
	public String stripeSessionId;
	
	public String stripePriceId;
	
	public String stripeSubscriptionId;
}
