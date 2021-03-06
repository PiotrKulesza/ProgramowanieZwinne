package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> studentRoute(StudentHandler studentHandler) {
        return RouterFunctions 
        		.route(GET("/students"), studentHandler::getStudenci)
				.andRoute(GET("/student/{id}"), studentHandler::getStudent)
				.andRoute(GET("/getStudentByLogin"), studentHandler::getStudentByLogin)
                .andRoute(GET("/getStudentByNrIndeksu"), studentHandler::getStudentByNrIndeksu)
                .andRoute(GET("/getStudentsByProject"), studentHandler::getStudentsByProject)
				.andRoute(DELETE("/students/{id}"), studentHandler::deleteStudent)
                .andRoute(GET("/getStudentInProject"), studentHandler::getStudentInProject)
                .andRoute(PUT("/updateStudent/{id}"), studentHandler::updateStudent)
                .andRoute(POST("/postStudent"), studentHandler::postStudent)
                .andRoute(DELETE("/deleteStudent/{id}"), studentHandler::deleteStudent)
                ;

    }
}
