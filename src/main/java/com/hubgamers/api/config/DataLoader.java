package com.hubgamers.api.config;

import com.hubgamers.api.fixtures.TeamDataFixtures;
import com.hubgamers.api.fixtures.UserDataFixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final UserDataFixtures userDataFixtures;
	private final TeamDataFixtures teamDataFixtures;
	
	@Autowired
	public DataLoader(UserDataFixtures userDataFixtures, TeamDataFixtures teamDataFixtures) {
		this.userDataFixtures = userDataFixtures;
		this.teamDataFixtures = teamDataFixtures;
	}
	
	@Override
	public void run(String... args) throws Exception {
		userDataFixtures.createTestData();
		teamDataFixtures.createTestData();
	}
}


