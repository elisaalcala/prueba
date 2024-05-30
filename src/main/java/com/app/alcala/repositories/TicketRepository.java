package com.app.alcala.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByTeamAssignAndStatusTicketNot(Team team, String excludedStatus);
	
	List<Ticket> findByEmployeeAssignAndStatusTicketNot(Employee employee, String excludedStatus);

	List<Ticket> findByEmployeeAssignAndStatusTicketIn(Employee employee, List<String> asList);

	List<Ticket> findByTeamAssignAndStatusTicketIn(Team team, List<String> asList);


}	
