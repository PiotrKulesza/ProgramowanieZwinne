package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> studentRoute(StudentHandler studentHandler) {
        return RouterFunctions 
        		.route(GET("/students"), studentHandler::getStudenci)
				.andRoute(GET("/students/{id}"), studentHandler::getStudent)
				.andRoute(GET("/getStudentByLogin"), studentHandler::getStudentByLogin)
				.andRoute(POST("/students").and(contentType(APPLICATION_JSON)), 
												studentHandler::createStudent)
				.andRoute(PUT("/students").and(contentType(APPLICATION_JSON)), 
												studentHandler::updateStudent)
				.andRoute(DELETE("/students/{id}"), studentHandler::deleteStudent);

    }
}
