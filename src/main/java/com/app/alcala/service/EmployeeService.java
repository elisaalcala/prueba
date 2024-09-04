package com.app.alcala.service;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Project;
import com.app.alcala.entities.Ticket;
import com.app.alcala.web.model.WorkPerEmployee;

public interface EmployeeService {

	Employee findByUserEmployee(String name);

	Employee findByEmployeeId(long id);

	Employee save(Employee employee);

	Employee deleteTicket(Employee employeeQuit, Ticket ticket);

	Employee deleteProject(Employee employeeAssign, Project project);

	WorkPerEmployee calculateWorkLoad(Employee employee);

	void delete(Employee employeeDelete);
	


}
