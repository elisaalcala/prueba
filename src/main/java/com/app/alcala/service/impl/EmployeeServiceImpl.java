package com.app.alcala.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Ticket;
import com.app.alcala.repositories.EmployeeRepository;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.web.model.WorkPerEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee findByUserEmployee(String name) {
		return employeeRepository.findByUserEmployee(name).orElseThrow();
	}

	@Override
	public Employee findByEmployeeId(long id) {
		return employeeRepository.findByEmployeeId(id).orElseThrow();
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteTicket(Employee employeeQuit, Ticket ticket) {
		employeeQuit.getTicketMapEmployee().remove(ticket.getIdTicket());
		return employeeRepository.save(employeeQuit);
	}

	@Override
	public Employee deleteProject(Employee employeeQuit, Project project) {
		employeeQuit.getProjectMapEmployee().remove(project.getIdProject());
		return employeeRepository.save(employeeQuit);
	}

	@Override
	public WorkPerEmployee calculateWorkLoad(Employee employee) {
		WorkPerEmployee workPerEmployee = new WorkPerEmployee();
		workPerEmployee.setIdEmployee(employee.getEmployeeId());
		workPerEmployee.setUserEmployee(employee.getUserEmployee());
		Integer load = 0;
		
		List<Project> projects = new ArrayList<>();
		for(Project project: employee.getProjectMapEmployee().values()) {
			if(project.getStatusProject().equalsIgnoreCase("In progress")) {
				projects.add(project);
				load++;
			}
		}
		
		List<Ticket> tickets = new ArrayList<>();
		for(Ticket ticket: employee.getTicketMapEmployee().values()) {
			if(ticket.getStatusTicket().equalsIgnoreCase("In Progress")) {
				tickets.add(ticket);
				load++;
			}
		}
		workPerEmployee.setProjectInProgress(projects);
		workPerEmployee.setTicketInProgress(tickets);
		workPerEmployee.setLoad(load);
		
		return workPerEmployee;
	}

	@Override
	public void delete(Employee employeeDelete) {
		employeeRepository.delete(employeeDelete);
		
	}

}
