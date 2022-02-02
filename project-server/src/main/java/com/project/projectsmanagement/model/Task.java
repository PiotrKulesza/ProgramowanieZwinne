package com.project.projectsmanagement.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	private Integer taskId;

	private Project project;

	private String nazwa;

	private String opis;

	private String kanban;

}
