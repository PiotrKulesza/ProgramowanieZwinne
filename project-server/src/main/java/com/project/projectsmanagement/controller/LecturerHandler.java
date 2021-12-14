package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.service.LecturerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LecturerHandler {

    private LecturerService lecturerService;

    public LecturerHandler(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }


    public Mono<ServerResponse> getLecturers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(lecturerService.findAll(), Lecturer.class);
    }

    public Mono<ServerResponse> getLecturer(ServerRequest request) {

        return lecturerService.getLecturer(Integer.valueOf(request.pathVariable("id")))
                .flatMap(lecturer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(lecturer));
    }

}
