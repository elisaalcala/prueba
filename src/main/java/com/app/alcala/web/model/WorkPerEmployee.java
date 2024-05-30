package com.app.alcala.web.model;

import java.util.List;

import com.app.alcala.entities.Project;
import com.app.alcala.entities.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPerEmployee {
	String userEmployee;
	Long idEmployee;
	Integer load;
	List<Project> projectInProgress;
	List<Ticket> ticketInProgress;

}
