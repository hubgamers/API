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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "username")
	public String username;
	
	@JsonIgnore
	@Column(name = "password")
	public String password;
	
	@JsonIgnore
	@Column(name = "refresh_token")
	public String refreshToken;

	@JsonIgnore
	@NotNull
	@Column(name = "email")
	public String email;

	@JsonIgnore
	@Column(name = "roles")
	public List<Role> roles;
	
	@Column(name = "avatar")
	public String avatar;

	@JsonIgnore
	@Column(name = "stripe_session_id")
	public String stripeSessionId;

	@JsonIgnore
	@Column(name = "stripe_price_id")
	public String stripePriceId;

	@JsonIgnore
	@Column(name = "stripe_subscription_id")
	public String stripeSubscriptionId;
}
