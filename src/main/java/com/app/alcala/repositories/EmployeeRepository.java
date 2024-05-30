package com.app.alcala.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmployeeId(Long employeeId);
	
	List<Employee> findAll();
	
	List<Employee> findByNameTeam(String nameTeam);
	
	Optional<Employee> findByUserEmployee(String userEmployee);
	
	
}
