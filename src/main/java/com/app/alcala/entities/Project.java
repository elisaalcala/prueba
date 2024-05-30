package com.app.alcala.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Table(name = "PROJECT")
@Data
@Entity
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long idProject;

    @Column(name = "name_project")
    private String nameProject;

    @Column(name = "title_project")
    private String titleProject;
    
    @Lob
    @Column(name = "description_project", columnDefinition = "LONGTEXT")
    private String descriptionProject;
    
    @Column(name = "type_project")
    private String typeProject;
    
    @Column(name = "priority_project")
    private String priorityProject;
    
    @Column(name = "environment_project")
    private String environmentProject;
    
    @Column(name = "initial_date")
    private Timestamp initialDate;
    
    @Column(name = "modify_date")
    private Timestamp modifyDate;
    
    @Column(name = "finish_date")
    private Timestamp finishDate;
    
    @Column(name = "status_project")
    private String statusProject;
    
    @Column(name = "release_name")
    private String releaseName;
    
    @Column(name = "team_name_assign")
    private String teamNameAssign;
    
    @Column(name = "employee_user_assign")
    private String employeeUserAssign;
    
    @Column(name = "employee_user_creation")
    private String employeeUserCreation;
    
    @Column(name = "employee_user_prj_manager")
    private String employeeUserPrjManager;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Release.class)
    private Release release;
    @JsonIgnore
    @ManyToOne(targetEntity = Team.class)
    private Team teamAssign;
    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeeAssign;
    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeeCreation;
    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeePrjManager;
}
