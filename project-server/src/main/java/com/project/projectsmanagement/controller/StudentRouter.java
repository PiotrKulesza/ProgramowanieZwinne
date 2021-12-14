package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration

public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> studentRoute(StudentHandler studentHandler) {
        return RouterFunctions 
        		.route()
				.GET("/students", studentHandler::getStudenci)
				.GET("/students/getStudent/{id}", studentHandler::getStudent)
				.GET("/students/getStudentByLogin"
						,studentHandler::getStudentByLogin)
        		.DELETE("/students/deleteStudent/{id}"
						,studentHandler::deleteStudent).build();


				//.andRoute(PUT("/student/updateStudent/{id}")
				//		.and(contentType(MediaType.APPLICATION_JSON)), studentHandler::updateStudent);

    }
}
