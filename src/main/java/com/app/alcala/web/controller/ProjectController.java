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
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Team;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private AlcalappService alcalappService;

	@GetMapping("")
	public String projectPage(Model model) {

		List<Project> projects = projectService.findAll();
		model.addAttribute("projects", projects);
		model.addAttribute("page", "Proyectos");
		return "projects";
	}

	@PostMapping("/create")
	public ResponseEntity<String> createProject(Model model,@RequestBody Project updatedProject, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		Project savedProject = alcalappService.save(updatedProject, employee);
		String redirectUrl = "/projects/" + savedProject.getIdProject();
		
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}
	
	@GetMapping("/{id}/find")
	public ResponseEntity<Project> findProjectPage(@PathVariable long id) {

		Project project = projectService.findById(id);
		return ResponseEntity.ok().body(project);
	}

	@GetMapping("/{id}")
	public String projectPage(Model model, @PathVariable long id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Team team = (Team) session.getAttribute("team");

		List<String> allStatus = new ArrayList<>(Arrays.asList("Backlog", "In Progress", "Closed", "Blocked", "Test", "Ready to UAT", "Ready to PRO", "Finish"));

		Project project = projectService.findById(id);

		model.addAttribute("project", project);
		model.addAttribute("allStatus", allStatus);
		model.addAttribute("allEmployees", team.getEmployeeMap().values());
		model.addAttribute("page", project.getNameProject());
		return "project";
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<String> updateProject(@PathVariable long id, @RequestBody Project updatedProject) {

		Project project = projectService.editProject(id, updatedProject);
		String redirectUrl = "/projects/" + project.getIdProject();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@PutMapping("/{id}/assign")
	public ResponseEntity<String> assignProject(@PathVariable long id, @RequestBody String user) {

		Project project = alcalappService.assignProject(id, user);
		String redirectUrl = "/projects/" + project.getIdProject();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteProject(@PathVariable long id) {

		Boolean deletedProject = alcalappService.deleteProject(id);
		String redirectUrl = "/projects";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

}
