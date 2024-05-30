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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.app.alcala.configuration.SecurityConfiguration;
import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.TicketService;
import com.app.alcala.service.impl.RepositoryUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TicketController.class)
@Import(SecurityConfiguration.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private AlcalappService alcalappService;

    @MockBean
    private RepositoryUserDetailsService userDetailsService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testTicketPage() throws Exception {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketService.findAll()).thenReturn(tickets);

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(model().attribute("tickets", tickets))
                .andExpect(model().attribute("page", "INCIDENCIAS"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        Employee employee = new Employee();
        Team team = new Team();
        when(alcalappService.save(any(Ticket.class), any(Employee.class), any(Team.class))).thenReturn(ticket);

        mockMvc.perform(post("/tickets/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket))
                .sessionAttr("employee", employee)
                .sessionAttr("team", team))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testTicketPageById() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setNameTicket("Test Ticket");
        Team team = new Team();
        team.setEmployeeMap(new HashMap<>());
        Employee employee = new Employee();
        employee.setEmployeeName("Test Employee");
        team.getEmployeeMap().put((long) 1, employee);

        when(ticketService.findById(anyLong())).thenReturn(ticket);

        List<String> allStatus = Arrays.asList("Backlog", "In Progress", "Resolved", "Closed");

        mockMvc.perform(get("/tickets/1").sessionAttr("team", team))
                .andExpect(status().isOk())
                .andExpect(view().name("ticket"))
                .andExpect(model().attribute("ticket", ticket))
                .andExpect(model().attribute("allStatus", allStatus))
                .andExpect(model().attribute("allEmployees", team.getEmployeeMap().values()))
                .andExpect(model().attribute("page", "Test Ticket"))
                .andExpect(model().attribute("historial", ticket.getMessageTicket()));
    }


    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testUpdateTicket() throws Exception {
    	
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        ticket.setNameTicket("Test Ticket");
        ticket.setDescriptionTicket("Description of the ticket");
        ticket.setStatusTicket("Open");

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("John Doe");

        when(ticketService.editTicket(anyLong(), any(Ticket.class), any(Employee.class))).thenReturn(ticket);

        MockHttpServletRequestBuilder requestBuilder = put("/tickets/1/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket))
                .sessionAttr("employee", employee);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"redirectUrl\": \"/tickets/1\"}"));
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testAssignTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        when(alcalappService.assignTicket(anyLong(), anyString())).thenReturn(ticket);

        mockMvc.perform(put("/tickets/1/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content("user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testMoveTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        when(alcalappService.moveTicket(anyLong(), anyString())).thenReturn(ticket);

        mockMvc.perform(put("/tickets/1/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content("nameTeam"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "johndoe", roles = "USER")
    public void testDeleteTicket() throws Exception {
        when(alcalappService.deleteTicket(anyLong(), any(Team.class))).thenReturn(true);

        mockMvc.perform(delete("/tickets/1/delete"))
                .andExpect(status().isOk());
    }
}

