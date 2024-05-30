package com.app.alcala.service;

import java.sql.Timestamp;

import com.app.alcala.entities.Message;

public interface MessageService {
	
	Message messageCreation(Timestamp date, String username, String teamName);
	Message messageAssign(Timestamp date, String username, String teamName);
	Message messageMove(Timestamp valueOf, String userEmployee, String teamName);
	Message messageFinish(Timestamp valueOf, String userEmployee);
	Message messageReOpen(Timestamp valueOf, String userEmployee);
}
