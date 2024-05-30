package com.app.alcala.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TICKET")
public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long idTicket;

    @Column(name = "name_ticket")
    private String nameTicket;
    
    @Column(name = "title_ticket")
    private String titleTicket;
    
    @Lob
    @Column(name = "description_ticket", columnDefinition = "LONGTEXT")
    private String descriptionTicket;
    
    @Column(name = "priority_ticket")
    private String priorityTicket;
    
    @Column(name = "environment_ticket")
    private String environmentTicket;
    
    @Column(name = "initial_date")
    private Timestamp initialDate;
    
    @Column(name = "modify_date")
    private Timestamp modifyDate;
    
    @Column(name = "finish_date")
    private Timestamp finishDate;
    
    @Column(name = "status_ticket")
    private String statusTicket;
    
    @OneToMany(targetEntity = Message.class, fetch = FetchType.EAGER)
    @Column(name = "message_ticket")
    private List<Message> messageTicket;
    
    @Column(name = "team_name_assign")
    private String teamNameAssign;
    
    @Column(name = "employee_user_assign")
    private String employeeUserAssign;
    
    @Column(name = "team_name_creation")
    private String teamNameCreation;
    
    @Column(name = "employee_user_creation")
    private String employeeUserCreation;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Team.class)
    private Team teamAssign;

    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeeAssign;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Team.class)
    private Team teamCreation;

    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeeCreation;
}
