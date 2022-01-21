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
        projectService.getProjectsByLecturer(Integer.valueOf(request.pathVariable("id"))).flatMap(project -> {System.out.println(project);
            return null;
        });
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProjectsByLecturer(Integer.valueOf(request.pathVariable("id"))), Project.class);



    }

}
