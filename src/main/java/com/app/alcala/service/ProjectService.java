package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;

public interface ProjectService {

	Project findById(long id);

	List<Project> findAll();

	List<Project> findProjectsNotCompletedByEmployee(Employee employee);

	List<Project> findByTeamAssignAndRelease(Team team, Release release);

	List<Project> findProjectsReadyByEmployee(Employee employee);

	Project save(Project project);

	Boolean delete(Project project);

	Project editProject(long id, Project updatedProject);

	Project mapNewProject(Project updatedProject, Employee employee, Team teamAssign, Release release);

	List<Project> findByRelease(Release release);

	List<Project> findProjectsFinishByEmployee(Employee employee);


	

}
