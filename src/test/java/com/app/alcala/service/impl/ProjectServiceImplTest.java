package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.repositories.ProjectRepository;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void testEditProject() {
        Project project = new Project();
        project.setIdProject(1L);
        project.setTitleProject("Old Title");
        project.setDescriptionProject("Old Description");
        project.setPriorityProject("High");
        project.setEnvironmentProject("Production");
        project.setStatusProject("In Progress");

        Project updatedProject = new Project();
        updatedProject.setTitleProject("New Title");
        updatedProject.setDescriptionProject("New Description");
        updatedProject.setPriorityProject("Low");
        updatedProject.setEnvironmentProject("Development");
        updatedProject.setStatusProject("Closed");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.editProject(1L, updatedProject);

        assertEquals("New Title", result.getTitleProject());
        assertEquals("New Description", result.getDescriptionProject());
        assertEquals("Low", result.getPriorityProject());
        assertEquals("Development", result.getEnvironmentProject());
        assertEquals("Closed", result.getStatusProject());
        assertNotNull(result.getFinishDate());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testSave() {
        Project project = new Project();

        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.save(project);

        assertEquals(project, result);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testFindById() {
        Project project = new Project();
        project.setIdProject(1L);

        when(projectRepository.findByIdProject(1L)).thenReturn(Optional.of(project));

        Project result = projectService.findById(1L);

        assertEquals(project, result);
    }

    @Test
    void testFindAll() {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testFindProjectsNotCompletedByEmployee() {
        Employee employee = new Employee();
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Arrays.asList("Backlog", "In progress", "Blocked"))).thenReturn(projects);

        List<Project> result = projectService.findProjectsNotCompletedByEmployee(employee);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testFindByTeamAssignAndRelease() {
        Team team = new Team();
        Release release = new Release();
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findByTeamAssignAndRelease(team, release)).thenReturn(projects);

        List<Project> result = projectService.findByTeamAssignAndRelease(team, release);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testFindProjectsReadyByEmployee() {
        Employee employee = new Employee();
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Arrays.asList("Test", "Ready to UAT", "Ready to PRO"))).thenReturn(projects);

        List<Project> result = projectService.findProjectsReadyByEmployee(employee);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testFindByRelease() {
        Release release = new Release();
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findByRelease(release)).thenReturn(projects);

        List<Project> result = projectService.findByRelease(release);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testMapNewProject() {
        Project updatedProject = new Project();
        Employee employee = new Employee();
        Team teamAssign = new Team();
        Release release = new Release();

        when(projectRepository.save(updatedProject)).thenReturn(updatedProject);

        Project result = projectService.mapNewProject(updatedProject, employee, teamAssign, release);

        assertEquals(employee, result.getEmployeeCreation());
        assertEquals(employee.getUserEmployee(), result.getEmployeeUserCreation());
        assertNotNull(result.getInitialDate());
        assertNotNull(result.getModifyDate());
        assertEquals(teamAssign, result.getTeamAssign());
        assertEquals("Backlog", result.getStatusProject());
        assertEquals(release, result.getRelease());
        assertNotNull(result.getNameProject());
        verify(projectRepository, times(1)).save(updatedProject);
    }

    @Test
    void testFindProjectsFinishByEmployee() {
        Employee employee = new Employee();
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.findByEmployeeAssignAndStatusProjectIn(employee, Arrays.asList("Finish"))).thenReturn(projects);

        List<Project> result = projectService.findProjectsFinishByEmployee(employee);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(projects));
    }

    @Test
    void testDelete() {
        Project project = new Project();

        assertTrue(projectService.delete(project));
        verify(projectRepository, times(1)).delete(project);
    }
}

