package com.app.alcala.web.model;

import java.sql.Timestamp;
import java.util.List;

import com.app.alcala.entities.Project;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTable {
	List<Project> projects;
	String releaseName;
	Long idRelease;
    Timestamp initialDate;
    Timestamp developDate;
    Timestamp tstDate;
    Timestamp uatDate;
    Timestamp proDate;
}
