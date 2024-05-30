package com.app.alcala.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.TicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	@Autowired
	private AlcalappService alcalappService;
	
	
	@GetMapping("")
	public String ticketPage(Model model) {

		List<Ticket> tickets = ticketService.findAll();

		model.addAttribute("tickets", tickets);
		model.addAttribute("page", "INCIDENCIAS");
		return "tickets";
	}
	
	@PostMapping("/create")
	public String createTicket(@RequestBody Ticket updatedTicket, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		Team team = (Team) session.getAttribute("team");

		Ticket savedTicket = alcalappService.save(updatedTicket, employee, team);
		return "redirect:/tickets/" + savedTicket.getIdTicket();
	}
	
	@GetMapping("/{id}")
	public String ticketPage(Model model, @PathVariable long id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Team team = (Team) session.getAttribute("team");

		List<String> allStatus = new ArrayList<>(Arrays.asList("Backlog", "In Progress", "Resolved", "Closed"));

		Ticket ticket = ticketService.findById(id);

		model.addAttribute("ticket", ticket);
		model.addAttribute("allStatus", allStatus);
		model.addAttribute("allEmployees", team.getEmployeeMap().values());
		model.addAttribute("page", ticket.getNameTicket());
		model.addAttribute("historial", ticket.getMessageTicket());
		
		return "ticket";
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<String> updateTicket(@PathVariable long id, @RequestBody Ticket updatedTicket,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		Ticket ticket = ticketService.editTicket(id, updatedTicket, employee);
		String redirectUrl = "/tickets/" + ticket.getIdTicket();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@PutMapping("/{id}/assign")
	public ResponseEntity<String> assignTicket(@PathVariable long id, @RequestBody String user) {

		Ticket ticket = alcalappService.assignTicket(id, user);

		String redirectUrl = "/tickets/" + ticket.getIdTicket();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@PutMapping("/{id}/move")
	public ResponseEntity<String> moveTicket(@PathVariable long id, @RequestBody String nameTeam) {

		Ticket ticket = alcalappService.moveTicket(id, nameTeam);

		String redirectUrl = "/tickets/" + ticket.getIdTicket();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteTicket(@PathVariable long id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Team team = (Team) session.getAttribute("team");

		Boolean deletedTicket = alcalappService.deleteTicket(id, team);
		String redirectUrl = "/tickets";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

}
