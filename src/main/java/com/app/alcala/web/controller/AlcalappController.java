package com.app.alcala.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.Ticket;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.ProjectService;
import com.app.alcala.service.ReleaseService;
import com.app.alcala.service.TeamService;
import com.app.alcala.service.TicketService;
import com.app.alcala.service.impl.RepositoryUserDetailsService;
import com.app.alcala.web.model.ProjectTable;
import com.app.alcala.web.model.WorkLoad;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AlcalappController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private AlcalappService alcalappService;
	@Autowired
	private RepositoryUserDetailsService userDetailsService;


	@GetMapping("/login")
	public String loginPage(@RequestParam(name = "error", required = false) String error, Model model) {
	    if (error != null && !error.isEmpty()) {
	        model.addAttribute("loginError", true);
	    }
	    return "login";
	}

	@GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        String redirectUrl = "/login";
        return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }
	
	@PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails.getPassword().equals(password)) {
                return "dailywork";
            } else {
                return "login";
            }
        } catch (UsernameNotFoundException e) {
            return "login";
        }
    }
	
	
	@GetMapping("/dailywork")
	public String dailyWorkPage(Model model, HttpServletRequest request) {

		String name = request.getUserPrincipal().getName();
		Employee employee = employeeService.findByUserEmployee(name);
		Team team = teamService.findByNameTeam(employee.getNameTeam());
		List<Team> createTicketTeamsList = teamService.findTeamsToSendTicket(team);
		
		List<Release> releasesOpen = releaseService.findByReleasesOpen();

		HttpSession session = request.getSession();
		session.setAttribute("employee", employee);
		session.setAttribute("team", team);
		session.setAttribute("createTicketTeamsList", createTicketTeamsList);
		session.setAttribute("releasesOpen", releasesOpen);

		List<ProjectTable> projectsTables = alcalappService.findProjectsPerRelease(team);
		List<Ticket> ticketsNotCompleted = ticketService.findticketsNotCompletedByTeam(team);
		WorkLoad workLoad = alcalappService.calculateWorkLoad(team);
		List<String> userPerEmployee = alcalappService.userPerEmployee(workLoad);
		List<String> loadPerEmployee = alcalappService.loadPerEmployee(workLoad);
		
		model.addAttribute("createTicketTeamsList", createTicketTeamsList);
		model.addAttribute("employee", employee);
		model.addAttribute("team", team);
		model.addAttribute("projectsTables", projectsTables);
		model.addAttribute("ticketsNotCompleted", ticketsNotCompleted);
		model.addAttribute("workLoad", workLoad);
		model.addAttribute("userPerEmployee", userPerEmployee);
		model.addAttribute("loadPerEmployee", loadPerEmployee);
		model.addAttribute("page", "TRABAJO DIARIO");
		
		return "dailywork";
	}

	@GetMapping("/mywork")
	public String myWorkPage(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		List<Project> projectsNotCompleted = projectService.findProjectsNotCompletedByEmployee(employee);
		List<Ticket> ticketsNotCompleted = ticketService.findTicketsNotCompletedByEmployee(employee);
		List<Project> projectsReady = projectService.findProjectsReadyByEmployee(employee);
		List<Ticket> ticketsReady = ticketService.findTicketsReadyByEmployee(employee);
		List<Project> projectsFinish = projectService.findProjectsFinishByEmployee(employee);
		List<Ticket> ticketsFinish = ticketService.findTicketsFinishByEmployee(employee);
		
		model.addAttribute("projectsNotCompleted", projectsNotCompleted);
		model.addAttribute("ticketsNotCompleted", ticketsNotCompleted);
		model.addAttribute("projectsReady", projectsReady);
		model.addAttribute("ticketsReady", ticketsReady);
		model.addAttribute("projectsFinish", projectsFinish);
		model.addAttribute("ticketsFinish", ticketsFinish);
		model.addAttribute("page", "MI TRABAJO");
		
		return "mywork";
	}

	@GetMapping("/profile")
	public String profilePage(Model model) {

		model.addAttribute("page", "MI PERFIL");
		
		return "profile";
	}


}