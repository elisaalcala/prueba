package com.app.alcala.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
	
	Optional<Team> findByNameTeam(String nameTeam);
	
	
}
