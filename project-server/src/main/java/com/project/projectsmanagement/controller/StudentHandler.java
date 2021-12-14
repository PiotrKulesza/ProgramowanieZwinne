package com.project.projectsmanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.service.StudentService;

import reactor.core.publisher.Mono;

@Component
public class StudentHandler {
	private StudentService studentService;

	public StudentHandler(StudentService studentService) {
		this.studentService = studentService;
	}

	public Mono<ServerResponse> getStudenci(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(studentService.getStudenci(), Student.class); 
	}

	
	public Mono<ServerResponse> updateStudent(ServerRequest request) {

		return request.bodyToMono(Student.class).flatMap(
				student -> this.studentService.updateStudent(student, Integer.valueOf(request.pathVariable("id"))))
				.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(student));

	}

	public Mono<ServerResponse> getStudent(ServerRequest request) {

		return studentService.getStudent(Integer.valueOf(request.pathVariable("id")))
				.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(student));
	}


	public Mono<ServerResponse> deleteStudent(ServerRequest request) {
		System.out.println("TEST");
		return Mono.just(studentService.deleteStudent(Integer.parseInt(request.pathVariable("id"))))
				.flatMap(val-> ServerResponse.noContent().build());


	}

	public Mono<ServerResponse> getStudentByLogin(ServerRequest request) {

		return studentService.getStudentByLogin(request.queryParam("email").get(),request.queryParam("password").get())
				.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(student));
	}

}
