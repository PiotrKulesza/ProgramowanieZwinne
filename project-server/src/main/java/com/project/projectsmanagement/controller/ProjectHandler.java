package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Project;
import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProjectHandler {

    private ProjectService projectService;

    public ProjectHandler(ProjectService projectService) {
        this.projectService = projectService;
    }



    public Mono<ServerResponse> getProjectsByLecturer(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProjectsByLecturer(Integer.valueOf(request.pathVariable("id"))), Project.class);
    }

    public Mono<ServerResponse> getProjectsByStudent(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProjectsByStudent(Integer.valueOf(request.pathVariable("id"))), Project.class);
    }

    public Mono<ServerResponse> getProjectsByProjectId(ServerRequest request) {

        if(request.queryParam("projectId").isPresent()) {

            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(projectService.getProjectsByProjectId(Integer.valueOf(request
                            .queryParam("projectId").get())), Project.class);
        }
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> postProject(ServerRequest request) {
        if(request.queryParam("lecturerId").isPresent() && request.queryParam("opis").isPresent()
                && request.queryParam("nazwa").isPresent())
            return projectService.postProject(request.queryParam("nazwa").get(),
                    request.queryParam("opis").get(),
                    Integer.valueOf(request.queryParam("lecturerId").get()))
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> addStudentToProject(ServerRequest request){

        if(request.queryParam("projectId").isPresent() && request.queryParam("studentId").isPresent()){
            return projectService.addStudentToProject(Integer.valueOf(request.queryParam("projectId").get()),
                    Integer.valueOf(request.queryParam("studentId").get()))
                    .then(ServerResponse.noContent().build());
        }
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> updateProject(ServerRequest request) {

        if(request.queryParam("opis").isPresent() && request.queryParam("projectId").isPresent())
            return projectService
                    .putOpis(Integer.valueOf(request.queryParam("projectId").get()), request.queryParam("opis").get())
                    .then(ServerResponse.noContent().build());
        else if(request.queryParam("nazwa").isPresent() && request.queryParam("projectId").isPresent())
            return projectService
                    .putNazwa(Integer.valueOf(request.queryParam("projectId").get()), request.queryParam("nazwa").get())
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> endProject(ServerRequest request) {
        if(request.queryParam("projectId").isPresent())
            return projectService
                    .putZakoncz(Integer.valueOf(request.queryParam("projectId").get()))
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> deleteProject(ServerRequest request) {
        if(request.queryParam("projectId").isPresent())
            return projectService
                    .deleteProject(Integer.valueOf(request.queryParam("projectId").get()))
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> deleteStudentFromProject(ServerRequest request){

        if(request.queryParam("projectId").isPresent() && request.queryParam("studentId").isPresent()){
            return projectService.deleteStudentFromProject(Integer.valueOf(request.queryParam("projectId").get()),
                    Integer.valueOf(request.queryParam("studentId").get()))
                    .then(ServerResponse.noContent().build());
        }
        return ServerResponse.badRequest().build();
    }

}
