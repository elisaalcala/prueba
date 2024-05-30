package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.repositories.ProjectRepository;
import com.app.alcala.service.ProjectService;
import com.mysql.cj.util.StringUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project findById(long id) {
		return projectRepository.findByIdProject(id).orElseThrow();
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public List<Project> findProjectsNotCompletedByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee,
				Arrays.asList("Backlog", "In progress", "Blocked"));
	}

	@Override
	public List<Project> findByTeamAssignAndRelease(Team team, Release release) {
		return projectRepository.findByTeamAssignAndRelease(team, release);
	}

	@Override
	public List<Project> findProjectsReadyByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee,
				Arrays.asList("Test", "Ready to UAT", "Ready to PRO"));
	}
	
	@Override
	public List<Project> findByRelease(Release release) {
		
		return projectRepository.findByRelease(release);
	}
	
	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Boolean delete(Project project) {
		try {
			projectRepository.delete(project);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Project editProject(long id, Project updatedProject) {
		Project project = projectRepository.findById(id).orElseThrow();

		if (!StringUtils.isNullOrEmpty(updatedProject.getTitleProject())) {
			project.setTitleProject(updatedProject.getTitleProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getDescriptionProject())) {
			project.setDescriptionProject(updatedProject.getDescriptionProject());
		}
		if (!StringUtils.isNullOrEmpty(updatedProject.getTypeProject())) {
			project.setTypeProject(updatedProject.getTypeProject());
		}
		if (!StringUtils.isNullOrEmpty(updatedProject.getPriorityProject())) {
			project.setPriorityProject(updatedProject.getPriorityProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getEnvironmentProject())) {
			project.setEnvironmentProject(updatedProject.getEnvironmentProject());
		}

		if (!StringUtils.isNullOrEmpty(updatedProject.getStatusProject())) {
			// Si el estado estaba en Resuelto y se cambia a No resuelto, hay que eliminar
			// la fecha de finalizacion
			if (!isCloseOrFinish(updatedProject) && isCloseOrFinish(project)) {
				project.setFinishDate(null);
			} else if (isCloseOrFinish(updatedProject) && !isCloseOrFinish(project)) {
				LocalDateTime currentDate = LocalDateTime.now();
				project.setFinishDate(Timestamp.valueOf(currentDate));

			}
			project.setStatusProject(updatedProject.getStatusProject());
		}
		LocalDateTime currentDate = LocalDateTime.now();
		project.setModifyDate(Timestamp.valueOf(currentDate));
		return projectRepository.save(project);
	}

	private boolean isCloseOrFinish(Project project) {
		return project.getStatusProject().equalsIgnoreCase("Closed");
	}

	
	public Project mapNewProject(Project updatedProject, Employee employee, Team teamAssign, Release release) {
		updatedProject.setEmployeeCreation(employee);
		updatedProject.setEmployeeUserCreation(employee.getUserEmployee());
		LocalDateTime currentDate = LocalDateTime.now();
		updatedProject.setInitialDate(Timestamp.valueOf(currentDate));
		updatedProject.setModifyDate(Timestamp.valueOf(currentDate));
		updatedProject.setTeamAssign(teamAssign);
		updatedProject.setStatusProject("Backlog");
		updatedProject.setRelease(release);
		save(updatedProject);
		updatedProject.setNameProject("PRJ " + updatedProject.getIdProject());
		return updatedProject;
	}

	@Override
	public List<Project> findProjectsFinishByEmployee(Employee employee) {
		return projectRepository.findByEmployeeAssignAndStatusProjectIn(employee,
				Arrays.asList("Finish"));
	}
	



}
