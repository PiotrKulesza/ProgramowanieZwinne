package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LoginHandler {
    private LoginService loginService;

    public LoginHandler(LoginService loginService) {
        this.loginService = loginService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return loginService.getUserById(Integer.valueOf(request.pathVariable("login_id")))
                .flatMap(login -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(login));
    }


}
