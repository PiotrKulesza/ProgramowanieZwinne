package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

@Configuration
public class ProjectRouter {
    @Bean
    public RouterFunction<ServerResponse> projectRoute(ProjectHandler projectHandler) {
        return RouterFunctions
                .route(GET("/lecturerProjects/{id}"), projectHandler::getProjectsByLecturer)
                .andRoute(POST("/addProject"),projectHandler::postProject)
                .andRoute(POST("/addStudentToProject"),projectHandler::addStudentToProject);


    }
}
