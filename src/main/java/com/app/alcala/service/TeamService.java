package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;

public interface TeamService {

	Team findByNameTeam(String nameTeam);

	List<Team> findTeamsToSendTicket(Team team);

	Team deleteTicket(Team teamQuit, Ticket ticket);

	Team deleteProject(Team team, Project project);
	
	Team save(Team team);

	void delete(Team teamDelete);

}
