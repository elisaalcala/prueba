package com.app.alcala.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TeamRepository;

@SpringBootTest
public class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void testFindByNameTeam() {
        Team team = new Team();
        team.setNameTeam("Team 1");

        when(teamRepository.findByNameTeam("Team 1")).thenReturn(Optional.of(team));

        Team result = teamService.findByNameTeam("Team 1");

        assertEquals("Team 1", result.getNameTeam());
    }

    @Test
    void testFindTeamsToSendTicket() {
        List<Team> teams = new ArrayList<>();
        Team team1 = new Team();
        team1.setNameTeam("Team 1");
        Team team2 = new Team();
        team2.setNameTeam("Team 2");
        teams.add(team1);
        teams.add(team2);

        when(teamRepository.findAll()).thenReturn(teams);

        Team team = new Team();
        team.setNameTeam("Team 1");

        List<Team> result = teamService.findTeamsToSendTicket(team);

        assertEquals(1, result.size());
        assertEquals("Team 2", result.get(0).getNameTeam());
    }

    @Test
    void testDeleteTicket() {
        Team team = new Team();
        Ticket ticket = new Ticket();
        ticket.setIdTicket(1L);
        team.setTicketMapTeam(new HashMap<>());
        team.getTicketMapTeam().put(1L, ticket);

        ArgumentCaptor<Team> teamCaptor = ArgumentCaptor.forClass(Team.class);

        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.deleteTicket(team, ticket);

        assertNull(result.getTicketMapTeam().get(1L));
        verify(teamRepository, times(1)).save(teamCaptor.capture());

        Team capturedTeam = teamCaptor.getValue();

        assertSame(team, capturedTeam);
    }
    @Test
    void testDeleteProject() {
        Team team = new Team();
        Project project = new Project();
        project.setIdProject(1L);
        team.setProjectMapTeam(new HashMap<>());
        team.getProjectMapTeam().put(1L, project);

        ArgumentCaptor<Team> teamCaptor = ArgumentCaptor.forClass(Team.class);

        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.deleteProject(team, project);

        assertNull(result.getProjectMapTeam().get(1L));

        verify(teamRepository, times(1)).save(teamCaptor.capture());

        Team capturedTeam = teamCaptor.getValue();

        assertSame(team, capturedTeam);
    }
}
