package com.hubgamers.api.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "team_roster")
public class TeamRoster {
	@Id
	public Long id;
	
	public String name;

	public String tag;
	
	public String description;
	
	public boolean visibility;
	
	public PaidType paidType;
	
	public String game;
	
	public String platform;
	
	public Region region;
	
	public List<Player> players = new ArrayList<>();
	
	public Long organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
	
	public List<Invitation> invitations;
	
	public enum PaidType {
		FREE, PREMIUM
	}
}
