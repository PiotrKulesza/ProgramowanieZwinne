package com.project.projectsmanagement.controller;

import java.net.URI;

import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Project;
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


	public Mono<ServerResponse> getStudent(ServerRequest request) {
		return studentService.getStudent(Integer.valueOf(request.pathVariable("id")))
				.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(student));
	}


	public Mono<ServerResponse> deleteStudent(ServerRequest request) {
		return Mono.just(studentService
					.deleteStudent(Integer.parseInt(request.pathVariable("id"))))
				.flatMap(val-> ServerResponse.noContent().build());


	}

	public Mono<ServerResponse> getStudentByLogin(ServerRequest request) {
		if(request.queryParam("email").isPresent() && request.queryParam("password").isPresent())
			return studentService.getStudentByLogin(request.queryParam("email").get(),request
					.queryParam("password").get())
					.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
							.bodyValue(student));
		return ServerResponse.badRequest().build();
	}

	public Mono<ServerResponse> getStudentByNrIndeksu(ServerRequest request) {
		if(request.queryParam("nrIndeksu").isPresent())
			return studentService.getStudentByNrIndeksu(request.queryParam("nrIndeksu").get())
					.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(student));
		return ServerResponse.badRequest().build();
	}

	public Mono<ServerResponse> getStudentsByProject(ServerRequest request) {
		if(request.queryParam("projectId").isPresent())
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(studentService.getStudentsByProject(Integer.valueOf(request.queryParam("projectId")
							.get())), Project.class);

		return ServerResponse.badRequest().build();
	}

	public Mono<ServerResponse> getStudentInProject(ServerRequest request) {
		if(request.queryParam("projectId").isPresent() && request.queryParam("studentId").isPresent())
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(studentService.getStudentInProject(Integer.valueOf(request.queryParam("projectId")
							.get()),Integer.valueOf(request.queryParam("studentId").get())
							), Project.class);

		return ServerResponse.badRequest().build();
	}

	public Mono<ServerResponse> postStudent(ServerRequest request) {
		if(request.queryParam("imie").isPresent() && request.queryParam("nazwisko").isPresent()
			&& request.queryParam("nrIndeksu").isPresent() && request.queryParam("loginId").isPresent()){
			Student student = new Student();
			Login login = new Login();
			login.setLoginId(Integer.valueOf(request.queryParam("loginId").get()));
			student.setLogin(login);
			student.setNazwisko(request.queryParam("nazwisko").get());
			student.setNrIndeksu(request.queryParam("nrIndeksu").get());
			student.setImie(request.queryParam("imie").get());
			return studentService.postStudent(student)
					.then(ServerResponse.noContent().build());
		}
		return ServerResponse.badRequest().build();

	}

	public Mono<ServerResponse> updateStudent(ServerRequest request) {

		if(request.queryParam("imie").isPresent())
			return studentService
					.updateImie(Integer.valueOf(request.pathVariable("id")), request.queryParam("imie").get())
					.then(ServerResponse.noContent().build());
		else if(request.queryParam("nazwisko").isPresent())
			return studentService
					.updateNazwisko(Integer.valueOf(request.pathVariable("id")), request.queryParam("nazwisko").get())
					.then(ServerResponse.noContent().build());
		else if(request.queryParam("nrIndeksu").isPresent())
			return studentService
					.updateNrIndeksu(Integer.valueOf(request.pathVariable("id")), request.queryParam("nrIndeksu").get())
					.then(ServerResponse.noContent().build());
		return ServerResponse.badRequest().build();
	}



}
