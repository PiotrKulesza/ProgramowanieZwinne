package com.project.projectsmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import javax.persistence.OneToOne;

@Table("student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@Column("student_id")
	private Integer studentId;

	private String imie;

	private String nazwisko;

	@Column("nrindeksu")
	private String nrIndeksu;

	private boolean stacjonarny;

	private Login login;

}