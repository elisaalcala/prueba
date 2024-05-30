package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Message;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.TicketRepository;
import com.app.alcala.service.MessageService;
import com.app.alcala.service.TicketService;
import com.mysql.cj.util.StringUtils;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	
	private MessageService messageService;

	public Ticket editTicket(long id, Ticket updatedTicket, Employee employee) {
		Ticket ticket = ticketRepository.findById(id).orElseThrow();

		if (!StringUtils.isNullOrEmpty(updatedTicket.getTitleTicket())) {
			ticket.setTitleTicket(updatedTicket.getTitleTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getDescriptionTicket())) {
			ticket.setDescriptionTicket(updatedTicket.getDescriptionTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getPriorityTicket())) {
			ticket.setPriorityTicket(updatedTicket.getPriorityTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getEnvironmentTicket())) {
			ticket.setEnvironmentTicket(updatedTicket.getEnvironmentTicket());
		}

		if (!StringUtils.isNullOrEmpty(updatedTicket.getStatusTicket())) {
			// Si el estado estaba en Resuelto y se cambia a No resuelto, hay que eliminar
			// la fecha de finalizacion
			if (!isCloseOrFinish(updatedTicket) && isCloseOrFinish(ticket)) {
				ticket.setFinishDate(null);
				LocalDateTime currentDate = LocalDateTime.now();
				Message message = messageService.messageReOpen(Timestamp.valueOf(currentDate),employee.getUserEmployee() );
				if (ObjectUtils.isEmpty(ticket.getMessageTicket())) {
					List<Message> mensajes = new ArrayList<>();
					mensajes.add(message);
					ticket.setMessageTicket(mensajes);
				}else ticket.getMessageTicket().add(message);
				
				
			} else if (isCloseOrFinish(updatedTicket) && !isCloseOrFinish(ticket)) {
				LocalDateTime currentDate = LocalDateTime.now();
				ticket.setFinishDate(Timestamp.valueOf(currentDate));
				Message message = messageService.messageFinish(Timestamp.valueOf(currentDate),employee.getUserEmployee() );
				if (ObjectUtils.isEmpty(ticket.getMessageTicket())) {
					List<Message> mensajes = new ArrayList<>();
					mensajes.add(message);
					ticket.setMessageTicket(mensajes);
				}else ticket.getMessageTicket().add(message);
				
			}
			ticket.setStatusTicket(updatedTicket.getStatusTicket());
		}
		LocalDateTime currentDate = LocalDateTime.now();
		ticket.setModifyDate(Timestamp.valueOf(currentDate));
		return ticketRepository.save(ticket);
	}

	private boolean isCloseOrFinish(Ticket ticket) {
		return ticket.getStatusTicket().equalsIgnoreCase("Closed")
				|| ticket.getStatusTicket().equalsIgnoreCase("Finish");
	}

	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public Ticket findById(long id) {
		return ticketRepository.findById(id).orElseThrow();
	}

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	public List<Ticket> findticketsNotCompletedByTeam(Team team) {
		return ticketRepository.findByTeamAssignAndStatusTicketIn(team,
				Arrays.asList("Backlog", "In progress", "Blocked"));
	}

	@Override
	public List<Ticket> findTicketsNotCompletedByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Backlog", "In progress", "Blocked"));
	}

	@Override
	public List<Ticket> findTicketsReadyByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Test", "Ready to UAT", "Ready to PRO"));
	}

	@Override
	public Ticket assignTicket(long id, Employee employee) {
		Ticket ticket = ticketRepository.findById(id).orElseThrow();
		ticket.setEmployeeAssign(employee);
		ticket.setEmployeeUserAssign(employee.getUserEmployee());
		return ticketRepository.save(ticket);
	}

	public Ticket mapNewTicket(Ticket updatedTicket, Employee employee, Team team, Team teamAssign, Message message, Message messageAssign) {

		updatedTicket.setEmployeeCreation(employee);
		updatedTicket.setEmployeeUserCreation(employee.getUserEmployee());
		updatedTicket.setTeamCreation(team);
		updatedTicket.setTeamNameCreation(team.getNameTeam());
		LocalDateTime currentDate = LocalDateTime.now();
		updatedTicket.setInitialDate(Timestamp.valueOf(currentDate));
		updatedTicket.setModifyDate(Timestamp.valueOf(currentDate));
		updatedTicket.setTeamAssign(teamAssign);
		updatedTicket.setStatusTicket("Backlog");
		List<Message> mensajes = new ArrayList<>();
		mensajes.add(message);
		mensajes.add(messageAssign);
		updatedTicket.setMessageTicket(mensajes);
		save(updatedTicket);
		updatedTicket.setNameTicket("TCK " + updatedTicket.getIdTicket());
		return updatedTicket;
	}

	@Override
	public Ticket moveTicket(Ticket ticket, Team team, Message message) {
		ticket.setTeamAssign(team);
		ticket.setTeamNameAssign(team.getNameTeam());
		ticket.setEmployeeAssign(null);
		ticket.setEmployeeUserAssign(null);
		ticket.setStatusTicket("Backlog");
		LocalDateTime currentDate = LocalDateTime.now();
		ticket.setInitialDate(Timestamp.valueOf(currentDate));
		ticket.setModifyDate(Timestamp.valueOf(currentDate));
		ticket.getMessageTicket().add(message);
		return ticketRepository.save(ticket);
	}

	@Override
	public Boolean delete(Ticket ticket) {
		try {
			ticketRepository.delete(ticket);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Ticket> findTicketsFinishByEmployee(Employee employee) {
		return ticketRepository.findByEmployeeAssignAndStatusTicketIn(employee,
				Arrays.asList("Closed", "Finish"));
	}

}
