package com.project.projectsmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {


	@Id
	@Column("login_id")
	private Integer loginId;

	private String email;

	private String password;

}
