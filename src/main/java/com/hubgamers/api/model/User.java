package com.hubgamers.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(generator = "uuid")
	public String id;
	
	@NotNull
	@Column(name = "username")
	public String username;
	
	@JsonIgnore
	@Column(name = "password")
	public String password;
	
	@JsonIgnore
	@Column(name = "refresh_token")
	public String refreshToken;
	
	@NotNull
	@Column(name = "email")
	public String email;
	
	@Column(name = "roles")
	public List<Role> roles;
	
	@Column(name = "stripe_session_id")
	public String stripeSessionId;
	
	@Column(name = "stripe_price_id")
	public String stripePriceId;
	
	@Column(name = "stripe_subscription_id")
	public String stripeSubscriptionId;
}
