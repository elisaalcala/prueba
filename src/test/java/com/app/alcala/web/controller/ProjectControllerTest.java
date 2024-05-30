package com.app.alcala.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.app.alcala.configuration.SecurityConfiguration;
import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.impl.RepositoryUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProjectController.class)
@Import(SecurityConfiguration.class)

public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private AlcalappService alcalappService;
    
    @MockBean
    private RepositoryUserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testProjectPage() throws Exception {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectService.findAll()).thenReturn(projects);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(view().name("projects"))
                .andExpect(model().attribute("projects", projects))
                .andExpect(model().attribute("page", "Proyectos"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setIdProject(1L);
        Employee employee = new Employee();
        when(alcalappService.save(any(Project.class), any(Employee.class))).thenReturn(project);

        mockMvc.perform(post("/projects/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project))
                .sessionAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect( content().json("{\"redirectUrl\": \"/projects/1\"}"));
    }
    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testFindProjectPage() throws Exception {
        Project project = new Project();
        when(projectService.findById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/projects/1/find"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(project)));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testProjectPageById() throws Exception {
        Project project = new Project();
        project.setNameProject("Test Project");
        Team team = new Team();
        team.setEmployeeMap(new HashMap<>());
        Employee employee = new Employee();
        employee.setEmployeeName("Test Employee");
        team.getEmployeeMap().put((long) 1, employee);

        when(projectService.findById(anyLong())).thenReturn(project);

        List<String> allStatus = Arrays.asList("Backlog", "In Progress", "Closed", "Blocked", "Test", "Ready to UAT", "Ready to PRO", "Finish");

        mockMvc.perform(get("/projects/1").sessionAttr("team", team))
                .andExpect(status().isOk())
                .andExpect(view().name("project"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("allStatus", allStatus))
                .andExpect(model().attribute("allEmployees", team.getEmployeeMap().values()))
                .andExpect(model().attribute("page", "Test Project"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testUpdateProject() throws Exception {
        Project project = new Project();
        project.setIdProject(1L);
        when(projectService.editProject(anyLong(), any(Project.class))).thenReturn(project);

        mockMvc.perform(put("/projects/1/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"redirectUrl\": \"/projects/1\"}"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testAssignProject() throws Exception {
        Project project = new Project();
        project.setIdProject(1L);
        when(alcalappService.assignProject(anyLong(), anyString())).thenReturn(project);

        mockMvc.perform(put("/projects/1/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content("user"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"redirectUrl\": \"/projects/1\"}"));
    }
    
    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testDeleteProject() throws Exception {
        when(alcalappService.deleteProject(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/projects/1/delete"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"redirectUrl\": \"/projects\"}"));
    }

}

