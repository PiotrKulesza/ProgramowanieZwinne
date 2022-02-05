package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TaskRouter {

    @Bean
    public RouterFunction<ServerResponse> taskRoute(TaskHandler taskHandler) {
        return RouterFunctions
                .route(GET("/getTaskByProject"),taskHandler::getTaskByProject)
                .andRoute(GET("/getTaskById"),taskHandler::getTaskById)
                .andRoute(POST("/addTask"),taskHandler::addTask)
                .andRoute(PUT("/updateTask"),taskHandler::updateTask)
                .andRoute(DELETE("/deleteTask"),taskHandler::deleteTask)
                .andRoute(DELETE("/deleteTaskByProject"),taskHandler::deleteTaskByProject)
                ;
    }
}
