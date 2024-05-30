package com.app.alcala.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.impl.RepositoryUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReleaseController.class)
@Import(SecurityConfiguration.class)
public class ReleaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReleaseService releaseService;

    @MockBean
    private AlcalappService alcalappService;
    
    @MockBean
    private RepositoryUserDetailsService userDetailsService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testReleasesPage() throws Exception {
        List<Release> releases = Arrays.asList(new Release(), new Release());
        when(releaseService.findAll()).thenReturn(releases);

        mockMvc.perform(get("/releases"))
                .andExpect(status().isOk())
                .andExpect(view().name("releases"))
                .andExpect(model().attribute("releases", releases))
                .andExpect(model().attribute("page", "Releases"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testCreateRelease() throws Exception {
        Release release = new Release();
        release.setIdRelease(1L);
        Employee employee = new Employee();
        when(releaseService.mapNewRelease(any(Release.class), any(Employee.class))).thenReturn(release);

        mockMvc.perform(post("/releases/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(release))
                .sessionAttr("employee", employee))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testReleasesPageById() throws Exception {
        Release release = new Release();
        release.setNameRelease("Test Release");
        
        release.setProjectMap(new HashMap<>());
        
        Team team = new Team();
        team.setEmployeeMap(new HashMap<>());
        Employee employee = new Employee();
        employee.setEmployeeName("Test Employee");
        team.getEmployeeMap().put((long) 1, employee);

        when(releaseService.findByIdRelease(anyLong())).thenReturn(release);

        List<String> allStatus = Arrays.asList("Backlog", "In Progress", "Resolved", "Closed");

        mockMvc.perform(get("/releases/1").sessionAttr("team", team))
                .andExpect(status().isOk())
                .andExpect(view().name("release"))
                .andExpect(model().attribute("release", release))
                .andExpect(model().attribute("projectMap", release.getProjectMap().values()))
                .andExpect(model().attribute("allStatus", allStatus))
                .andExpect(model().attribute("allEmployees", team.getEmployeeMap().values()))
                .andExpect(model().attribute("page", "Test Release"));
    }


    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testUpdateRelease() throws Exception {
        Release release = new Release();
        release.setIdRelease(1L);
        when(releaseService.editRelease(anyLong(), any(Release.class))).thenReturn(release);

        mockMvc.perform(put("/releases/1/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(release)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testDeleteRelease() throws Exception {
        when(alcalappService.deleteRelease(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/releases/1/delete"))
                .andExpect(status().isOk());
    }
}

