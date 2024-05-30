package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.MessageService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.WorkLoad;
import com.app.alcala.web.model.WorkPerEmployee;

import io.micrometer.common.util.StringUtils;

@Service
public class AlcalappServiceImpl implements AlcalappService {
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MessageService messageService;

	@Override
	public List<ProjectTable> findProjectsPerRelease(Team team) {
		List<ProjectTable> projectsTables = new ArrayList<>();
		List<Release> releaseNotCompleted = releaseService.findByReleaseNotCompleted();
		for (Release release : releaseNotCompleted) {
			List<Project> projectsNotCompleted = projectService.findByTeamAssignAndRelease(team, release);
			ProjectTable projectTable = new ProjectTable();
			projectTable.setProjects(projectsNotCompleted);
			projectTable.setReleaseName(release.getNameRelease());
			projectTable.setIdRelease(release.getIdRelease());
			projectTable.setInitialDate(release.getInitialDate());
			projectTable.setProDate(release.getProDate());
			projectTable.setTstDate(release.getTstDate());
			projectTable.setUatDate(release.getUatDate());
			projectsTables.add(projectTable);
		}
		return projectsTables;
	}

	@Override
	public Ticket save(Ticket updatedTicket, Employee employee, Team team) {
		Team teamAssign = teamService.findByNameTeam(updatedTicket.getTeamNameAssign());
		LocalDateTime currentDate = LocalDateTime.now();
		Message message = messageService.messageCreation(Timestamp.valueOf(currentDate),employee.getUserEmployee(), team.getNameTeam() );
		Message messageAssign = messageService.messageAssign(Timestamp.valueOf(currentDate),employee.getUserEmployee(), teamAssign.getNameTeam() );
		Ticket updated = ticketService.mapNewTicket(updatedTicket, employee, team, teamAssign, message, messageAssign);
		teamAssign.getTicketMapTeam().put(updatedTicket.getIdTicket(), updatedTicket);
		
		return ticketService.save(updated);
	}

	@Override
	public Ticket assignTicket(long id, String user) {
		String result = user.substring(1, user.length() - 1);
		Ticket ticket = ticketService.findById(id);
		if (result.equalsIgnoreCase("Sin asignar")) {
			if (!ObjectUtils.isEmpty(ticket.getEmployeeAssign())) {
				Employee employeeQuit = ticket.getEmployeeAssign();
				employeeQuit.getTicketMapEmployee().remove(ticket.getIdTicket());
				ticket.setEmployeeAssign(null);
				ticket.setEmployeeUserAssign("");
				employeeService.save(employeeQuit);
			}
		} else {
			Employee employeeUpdate = employeeService.findByUserEmployee(result);
			ticket.setEmployeeAssign(employeeUpdate);
			ticket.setEmployeeUserAssign(employeeUpdate.getUserEmployee());
			employeeUpdate.getTicketMapEmployee().put(id, ticket);
			employeeService.save(employeeUpdate);
		}
		return ticketService.save(ticket);
	}

	@Override
	public Ticket moveTicket(long id, String nameTeam) {
		String nameTeamFinal = nameTeam.substring(1, nameTeam.length() - 1);
		Team team = teamService.findByNameTeam(nameTeamFinal);
		Ticket ticket = ticketService.findById(id);
		Employee employeeQuit = ticket.getEmployeeAssign();
		Team teamQuit = ticket.getTeamAssign();
		employeeService.deleteTicket(employeeQuit, ticket);
		teamService.deleteTicket(teamQuit, ticket);
		LocalDateTime currentDate = LocalDateTime.now();
		Message message = messageService.messageMove(Timestamp.valueOf(currentDate),employeeQuit.getUserEmployee(),team.getNameTeam() );
		return ticketService.moveTicket(ticket, team, message);
	}

	@Override
	public Boolean deleteTicket(long id, Team team) {
		Ticket ticket = ticketService.findById(id);
		if ((!StringUtils.isEmpty(ticket.getEmployeeUserAssign()))
				&& (!ObjectUtils.isEmpty(ticket.getEmployeeAssign())))
			employeeService.deleteTicket(ticket.getEmployeeAssign(), ticket);
		teamService.deleteTicket(team, ticket);
		return ticketService.delete(ticket);
	}

	@Override
	public Project assignProject(long id, String user) {
		String result = user.substring(1, user.length() - 1);
		Project project = projectService.findById(id);
		if (result.equalsIgnoreCase("Sin asignar")) {
			if (!ObjectUtils.isEmpty(project.getEmployeeAssign())) {
				Employee employeeQuit = project.getEmployeeAssign();
				employeeQuit.getProjectMapEmployee().remove(project.getIdProject());
				project.setEmployeeAssign(null);
				project.setEmployeeUserAssign("");
				employeeService.save(employeeQuit);
			}
		} else {
			Employee employeeUpdate = employeeService.findByUserEmployee(result);
			project.setEmployeeAssign(employeeUpdate);
			project.setEmployeeUserAssign(employeeUpdate.getUserEmployee());
			employeeUpdate.getProjectMapEmployee().put(id, project);
			employeeService.save(employeeUpdate);
		}
		return projectService.save(project);
	}

	@Override
	public Boolean deleteProject(long id) {
		Project project = projectService.findById(id);
		Team teamAssign = teamService.findByNameTeam(project.getTeamNameAssign());
		if ((!StringUtils.isEmpty(project.getEmployeeUserAssign()))
				&& (!ObjectUtils.isEmpty(project.getEmployeeAssign())))
			employeeService.deleteProject(project.getEmployeeAssign(), project);
		teamService.deleteProject(teamAssign, project);
		return projectService.delete(project);
	}

	@Override
	public Project save(Project updatedProject, Employee employee) {
		Team teamAssign = teamService.findByNameTeam(updatedProject.getTeamNameAssign());
		Release release = releaseService.findByNameRelease(updatedProject.getReleaseName());
		Project updated = projectService.mapNewProject(updatedProject, employee, teamAssign, release);
		teamAssign.getProjectMapTeam().put(updatedProject.getIdProject(), updatedProject);
		release.getProjectMap().put(updated.getIdProject(), updated);
		return projectService.save(updated);
	}

	@Override
	public Boolean deleteRelease(long id) {
		Release release = releaseService.findByIdRelease(id);
		for(Long deleteProjectId : release.getProjectMap().keySet())
			deleteProject(deleteProjectId);
		return releaseService.delete(release);
	}

	@Override
	public WorkLoad calculateWorkLoad(Team team) {
		WorkLoad workLoad = new WorkLoad();
		List<WorkPerEmployee> workPerEmployee = new ArrayList<>();
		for(Employee employee: team.getEmployeeMap().values()) {
			workPerEmployee.add(employeeService.calculateWorkLoad(employee));
		}
		workLoad.setListWorkPerEmployee(workPerEmployee);
		return workLoad;
	}
	
	public List<String> loadPerEmployee(WorkLoad workload){
		List<String> list = new ArrayList<String>();
		for (WorkPerEmployee perEmplpoyee : workload.getListWorkPerEmployee())
			list.add(('"'+ (perEmplpoyee.getLoad().toString()) +'"'));
		return list;
	}
	public List<String> userPerEmployee(WorkLoad workload){
		List<String> list = new ArrayList<String>();
		for (WorkPerEmployee perEmplpoyee : workload.getListWorkPerEmployee())
			list.add('"'+ perEmplpoyee.getUserEmployee()+'"');
		return list;
	}

}
