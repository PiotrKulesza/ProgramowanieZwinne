package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Configuration
public class LecturerRouter {

    @Bean
    public RouterFunction<ServerResponse> lecturerRoute(LecturerHandler lecturerHandler) {
        return RouterFunctions
                .route(GET("/lecturers"), lecturerHandler::getLecturers)
                .andRoute(GET("/lecturer/{id}"), lecturerHandler::getLecturer)
                .andRoute(GET("/getLecturerByLogin"), lecturerHandler::getLecturerByLogin)
                .andRoute(PUT("/updateLecturer/{id}"), lecturerHandler::updateLecturer);
                /*
                .route().GET("/lecturers", lecturerHandler::getLecturers)
                .GET("/lecturer/getLecturer/{id}", lecturerHandler::getLecturer).build();*/
    }


}
