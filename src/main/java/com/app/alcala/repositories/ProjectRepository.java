package com.app.alcala.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	Optional<Project> findByNameProject(String nameProject);
	
	List<Project> findByTeamNameAssign(String teamName);
	
	List<Project> findByTeamAssignAndStatusProjectNot(Team team, String status);

	List<Project> findByTeamAssignAndRelease(Team team, Release release);
	
	List<Project> findByEmployeeAssignAndStatusProjectIn(Employee employee, List<String> status);

	List<Project> findByRelease(Release release);

	Optional<Project> findByIdProject(long id);}
