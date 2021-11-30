package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> studentRoute(StudentHandler studentHandler) {
        return RouterFunctions.route(PUT("/student/updateStudent/{id}").and(contentType(MediaType.APPLICATION_JSON))
                ,studentHandler::updateStudent)
                .andRoute(GET("/student/getStudent/{id}").and(contentType(MediaType.APPLICATION_JSON))
                        ,studentHandler::getStudent)
                .andRoute(DELETE("student/deleteStudent/{id}").and(contentType(MediaType.APPLICATION_JSON))
                        ,studentHandler::deleteStudent);
    }
}
