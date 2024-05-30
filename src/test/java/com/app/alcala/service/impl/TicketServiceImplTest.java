package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TicketRepository;
import com.app.alcala.service.MessageService;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void testEditTicket() {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        ticket.setTitleTicket("Old Title");
        ticket.setDescriptionTicket("Old Description");
        ticket.setPriorityTicket("High");
        ticket.setEnvironmentTicket("Production");
        ticket.setStatusTicket("In Progress");

        Ticket updatedTicket = new Ticket();
        updatedTicket.setTitleTicket("New Title");
        updatedTicket.setDescriptionTicket("New Description");
        updatedTicket.setPriorityTicket("Low");
        updatedTicket.setEnvironmentTicket("Development");
        updatedTicket.setStatusTicket("Closed");

        Employee employee = new Employee();
        employee.setUserEmployee("JohnDoe");

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(messageService.messageFinish(any(Timestamp.class), eq(employee.getUserEmployee()))).thenReturn(new Message());
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        
        Ticket result = ticketService.editTicket(1L, updatedTicket, employee);

        assertEquals("New Title", result.getTitleTicket());
        assertEquals("New Description", result.getDescriptionTicket());
        assertEquals("Low", result.getPriorityTicket());
        assertEquals("Development", result.getEnvironmentTicket());
        assertEquals("Closed", result.getStatusTicket());
        assertNotNull(result.getFinishDate());
        verify(ticketRepository, times(1)).save(ticket);
    }


    @Test
    void testSave() {
        Ticket ticket = new Ticket();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.save(ticket);

        assertEquals(ticket, result);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testFindById() {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.findById(1L);

        assertEquals(ticket, result);
    }

    @Test
    void testFindAll() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(tickets));
    }

    @Test
    void testAssignTicket() {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        Employee employee = new Employee();
        employee.setUserEmployee("user");

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.assignTicket(1L, employee);

        assertEquals(employee, result.getEmployeeAssign());
        assertEquals(employee.getUserEmployee(),result.getEmployeeUserAssign());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testMapNewTicket() {
        Ticket updatedTicket = new Ticket();
        updatedTicket.setIdTicket(1L);
        updatedTicket.setTitleTicket("New Ticket");

        Employee employee = new Employee();
        employee.setUserEmployee("JohnDoe");

        Team team = new Team();
        team.setNameTeam("Team 1");

        Team teamAssign = new Team();
        teamAssign.setNameTeam("Team 2");

        Message message = new Message();
        Message messageAssign = new Message();

        when(ticketRepository.save(updatedTicket)).thenReturn(updatedTicket);

        Ticket result = ticketService.mapNewTicket(updatedTicket, employee, team, teamAssign, message, messageAssign);

        assertEquals(employee, result.getEmployeeCreation());
        assertNotNull(result.getEmployeeUserCreation());
        assertEquals(team, result.getTeamCreation());
        assertEquals("Team 1", result.getTeamNameCreation());
        assertNotNull(result.getInitialDate());
        assertNotNull(result.getModifyDate());
        assertEquals(teamAssign, result.getTeamAssign());
        assertEquals("Backlog", result.getStatusTicket());
        assertNotNull(result.getMessageTicket());
        assertEquals(2, result.getMessageTicket().size());
        assertEquals("TCK 1", result.getNameTicket());
        verify(ticketRepository, times(1)).save(updatedTicket);
    }

    @Test
    void testMoveTicket() {
        Ticket ticket = new Ticket();
        ticket.setMessageTicket(new ArrayList<>());
        Team team = new Team();
        Message message = new Message();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.moveTicket(ticket, team, message);

        assertEquals(team, result.getTeamAssign());
        assertEquals("Backlog", result.getStatusTicket());
        assertNotNull(result.getInitialDate());
        assertNotNull(result.getModifyDate());
        assertEquals(1, result.getMessageTicket().size());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testDelete() {
        Ticket ticket = new Ticket();

        assertTrue(ticketService.delete(ticket));
        verify(ticketRepository, times(1)).delete(ticket);
    }
}
