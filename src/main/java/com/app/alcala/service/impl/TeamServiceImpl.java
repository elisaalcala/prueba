package com.app.alcala.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TeamRepository;
import com.app.alcala.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	TeamRepository teamRepository;

	@Override
	public Team findByNameTeam(String nameTeam) {
		return teamRepository.findByNameTeam(nameTeam).orElseThrow();
	}

	@Override
	public List<Team> findTeamsToSendTicket(Team team) {
		List<Team> createTicketTeamsList = teamRepository.findAll();
		createTicketTeamsList.remove(team);
		return createTicketTeamsList;
	}

	@Override
	public Team deleteTicket(Team teamQuit, Ticket ticket) {
		teamQuit.getTicketMapTeam().remove(ticket.getIdTicket());
		return teamRepository.save(teamQuit);
	}

	@Override
	public Team deleteProject(Team teamQuit, Project project) {
		teamQuit.getProjectMapTeam().remove(project.getIdProject());
		return teamRepository.save(teamQuit);
	}
	
}
