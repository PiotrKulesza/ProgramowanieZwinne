package com.project.projectsmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Table ("project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@Id
	@Column("project_id")
	private Integer projectId;

	private String nazwa;

	private String opis;

	private LocalDateTime dataczasUtworzenia;

	private LocalDateTime dataczasEdycji;

	private LocalDate dataOddania;

	private Lecturer lecturer;

}
