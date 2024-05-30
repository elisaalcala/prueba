package com.app.alcala.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RELEASES")
public class Release implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_release")
    private Long idRelease;

    @Column(name = "name_release")
    private String nameRelease;

    @Column(name = "initial_date")
    private Timestamp initialDate;
    
    @Column(name = "requirements_date")
    private Timestamp requirementsDate;
    
    @Column(name = "prj_assignment_date")
    private Timestamp prjAssignmentDate;
    
    @Column(name = "develop_date")
    private Timestamp developDate;
    
    @Column(name = "tst_date")
    private Timestamp tstDate;
    
    @Column(name = "uat_date")
    private Timestamp uatDate;
    
    @Column(name = "pro_date")
    private Timestamp proDate;
    
    
    @Column(name = "status_release")
    private String statusRelease;
    
    @Column(name = "employee_user_creation")
    private String employeeUserCreation;

    @OneToMany(targetEntity = Project.class, fetch = FetchType.EAGER, mappedBy = "release")
    @MapKey(name = "idProject")
    private Map<Long, Project> projectMap;
    
    @ManyToOne(targetEntity = Employee.class)
    private Employee employeeCreation;
}
