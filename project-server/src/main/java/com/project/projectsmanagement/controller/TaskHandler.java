package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Task;
import com.project.projectsmanagement.service.TaskService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class TaskHandler {

    private TaskService taskService;

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    public Mono<ServerResponse> getTaskByProject(ServerRequest request){
        if(request.queryParam("projectId").isPresent()){

            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(taskService.getTaskByProject(Integer.valueOf(request.queryParam("projectId").get())), Task.class);

        }

        return ServerResponse.badRequest().build();
    }
}
