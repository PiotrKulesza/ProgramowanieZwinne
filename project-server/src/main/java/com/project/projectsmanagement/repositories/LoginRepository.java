package com.project.projectsmanagement.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.projectsmanagement.model.Login;

import reactor.core.publisher.Mono;

@Repository
public interface LoginRepository extends ReactiveCrudRepository<Login, Integer> {

	//Mono<Login> findLoginByEmailAndPassword(String email, String password);

	@Query("SELECT login.* FROM login WHERE login.email=:email")
	Mono<Login> findLoginByEmail(String email);

}
