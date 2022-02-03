package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Project;
import com.project.projectsmanagement.model.Student;
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

    public Mono<ServerResponse> addTask(ServerRequest request) {
        if(request.queryParam("projectId").isPresent() && request.queryParam("nazwa").isPresent()
                && request.queryParam("opis").isPresent()){
            System.out.println("Test");
            Task task = new Task();
            Project project = new Project();
            project.setProjectId(Integer.valueOf(request.queryParam("projectId").get()));
            task.setProject(project);
            task.setNazwa(request.queryParam("nazwa").get());
            task.setOpis(request.queryParam("opis").get());
            return taskService.postTask(task)
                    .then(ServerResponse.noContent().build());
        }
        return ServerResponse.badRequest().build();

    }


    public Mono<ServerResponse> getTaskById(ServerRequest request){
        if(request.queryParam("taskId").isPresent()){

            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(taskService.getTaskById(Integer.valueOf(request.queryParam("taskId").get())), Task.class);

        }

        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> updateTask(ServerRequest request){
        if(request.queryParam("taskId").isPresent())
            if(request.queryParam("nazwa").isPresent())
                return taskService
                        .putNazwa(Integer.valueOf(request.queryParam("taskId").get()), request.queryParam("nazwa").get())
                        .then(ServerResponse.noContent().build());
            else if(request.queryParam("opis").isPresent())
                return taskService
                        .putOpis(Integer.valueOf(request.queryParam("taskId").get()), request.queryParam("opis").get())
                        .then(ServerResponse.noContent().build());
            else if(request.queryParam("kanban").isPresent())
                return taskService
                        .putKanban(Integer.valueOf(request.queryParam("taskId").get()), request.queryParam("kanban").get())
                        .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();

    }



}
