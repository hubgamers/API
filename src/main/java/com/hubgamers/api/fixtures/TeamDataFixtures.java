package com.hubgamers.api.fixtures;

import com.hubgamers.api.model.Team;
import com.hubgamers.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TeamDataFixtures {
	
	private final TeamRepository teamRepository;
	
	@Autowired
	public TeamDataFixtures(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	
	public void createTestData() {
		Set<String> names = Set.of("Team 1", "Team 2", "Team 3", "Team 4", "Team 5");
		if (teamRepository.count() == 0) {
			for (String name : names) {
				Team team = new Team();
				team.setName(name);
				teamRepository.save(team);
			}	
		}
	}
}
