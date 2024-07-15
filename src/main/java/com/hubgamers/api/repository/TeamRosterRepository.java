package com.hubgamers.api.repository;

import com.hubgamers.api.model.TeamRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRosterRepository extends JpaRepository<TeamRoster, Long> {
	
	List<TeamRoster> findAllByNameLike(String name);
	
	List<TeamRoster> findByPlayersId(Long playerId);
	
	List<TeamRoster> findAllByOrganizerId(Long organizerId);
	
	List<TeamRoster> findAllByTeamId(Long teamId);
	
	List<TeamRoster> findAllByVisibility(boolean visibility);
	
	Optional<TeamRoster> findByName(String name);
	
	Optional<TeamRoster> findByOrganizerId(Long organizerId);
}
