package com.project.projectsmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	@Id
	private Long messaged;

	private String text;

	private Student sender;

	private Student receiver;

}