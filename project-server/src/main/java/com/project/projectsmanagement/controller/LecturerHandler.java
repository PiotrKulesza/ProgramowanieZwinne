package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.service.LecturerService;

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

    public Mono<ServerResponse> getLecturerByLogin(ServerRequest request) {

        return lecturerService.getLecturerByLogin(request.queryParam("email").get(),request.queryParam("password").get())
                .flatMap(lecturer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(lecturer));
    }

    public Mono<ServerResponse> updateLecturer(ServerRequest request) {
        if(request.queryParam("imie").isPresent())
            return lecturerService
                .updateImie(Integer.valueOf(request.pathVariable("id")), request.queryParam("imie").get())
                .then(ServerResponse.noContent().build());
        else if(request.queryParam("nazwisko").isPresent())
            return lecturerService
                    .updateNazwisko(Integer.valueOf(request.pathVariable("id")), request.queryParam("nazwisko").get())
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> postLecturer(ServerRequest request) {
        if(request.queryParam("imie").isPresent() && request.queryParam("nazwisko").isPresent()
                &&  request.queryParam("loginId").isPresent()){
            Lecturer lecturer = new Lecturer();
            Login login = new Login();
            login.setLoginId(Integer.valueOf(request.queryParam("loginId").get()));
            lecturer.setLogin(login);
            lecturer.setNazwisko(request.queryParam("nazwisko").get());
            lecturer.setImie(request.queryParam("imie").get());
            return lecturerService.postLecturer(lecturer)
                    .then(ServerResponse.noContent().build());
        }
        return ServerResponse.badRequest().build();
    }

}
