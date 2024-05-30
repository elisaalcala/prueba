package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.WorkLoad;

public interface AlcalappService {

	List<ProjectTable> findProjectsPerRelease(Team team);

	Ticket save(Ticket updatedTicket, Employee employee, Team team);

	Ticket assignTicket(long id, String user);

	Ticket moveTicket(long id, String nameTeam);

	Boolean deleteTicket(long id, Team team);

	Project assignProject(long id, String user);

	Boolean deleteProject(long id);

	Project save(Project updatedProject, Employee employee);

	Boolean deleteRelease(long id);

	WorkLoad calculateWorkLoad(Team team);

	List<String> userPerEmployee(WorkLoad workLoad);

	List<String> loadPerEmployee(WorkLoad workLoad);


}
