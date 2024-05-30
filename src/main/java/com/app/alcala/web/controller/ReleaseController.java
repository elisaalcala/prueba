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
import com.app.alcala.entities.Release;
import com.app.alcala.entities.Team;
import com.app.alcala.service.AlcalappService;
import com.app.alcala.service.ReleaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/releases")
public class ReleaseController {
	
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private AlcalappService alcalappService;
	

	@GetMapping("")
	public String releasesPage(Model model) {

		List<Release> releases = releaseService.findAll();

		model.addAttribute("releases", releases);
		model.addAttribute("page", "Releases");
		return "releases";
	}
	
	@PostMapping("/create")
	public String createRelease(@RequestBody Release updatedRelease, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		Release savedRelease = releaseService.mapNewRelease(updatedRelease, employee);
		return "redirect:/releases/" + savedRelease.getIdRelease();
	}
	
	@GetMapping("/{id}")
	public String releasesPage(Model model, @PathVariable long id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Team team = (Team) session.getAttribute("team");

		List<String> allStatus = new ArrayList<>(Arrays.asList("Backlog", "In Progress", "Resolved", "Closed"));

		Release release = releaseService.findByIdRelease(id);

		model.addAttribute("release", release);
		model.addAttribute("projectMap", release.getProjectMap().values());
		model.addAttribute("allStatus", allStatus);
		model.addAttribute("allEmployees", team.getEmployeeMap().values());
		model.addAttribute("page", release.getNameRelease());
		return "release";
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<String> updateRelease(@PathVariable long id, @RequestBody Release updatedRelease) {

		Release release = releaseService.editRelease(id, updatedRelease);
		String redirectUrl = "/releases/" + release.getIdRelease();
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteRelease(@PathVariable long id) {

		Boolean deletedRelease = alcalappService.deleteRelease(id);
		String redirectUrl = "/releases";
		return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
	}

}
