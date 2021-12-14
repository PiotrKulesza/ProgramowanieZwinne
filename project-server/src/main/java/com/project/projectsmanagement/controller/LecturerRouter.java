package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LecturerRouter {

    @Bean
    public RouterFunction<ServerResponse> lecturerRoute(LecturerHandler lecturerHandler) {
        return RouterFunctions
                .route().GET("/lecturers", lecturerHandler::getLecturers)
                .GET("/lecturer/getLecturer/{id}", lecturerHandler::getLecturer).build();
    }


}
