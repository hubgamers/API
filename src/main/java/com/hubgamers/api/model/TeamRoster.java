package com.hubgamers.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "team_roster")
public class TeamRoster {
	@Id
	public String id;
	
	public String name;

	public String tag;
	
	public String description;
	
	public boolean visibility;
	
	public PaidType paidType;
	
	public String game;
	
	public String platform;
	
	public Region region;
	
	public List<Player> players = new ArrayList<>();
	
	public String organizerId;
	
	public String logo;
	
	public String banner;
	
	public SocialMedia socialMedia;
	
	public List<Invitation> invitations;
	
	public enum PaidType {
		FREE, PREMIUM
	}
}
