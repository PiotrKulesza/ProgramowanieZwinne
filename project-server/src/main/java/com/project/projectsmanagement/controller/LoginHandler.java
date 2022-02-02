package com.project.projectsmanagement.controller;


import com.project.projectsmanagement.model.Login;
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

    public Mono<ServerResponse> updatePassword(ServerRequest request) {
        System.out.println("Test");
        if(request.queryParam("loginId").isPresent() && request.queryParam("oldPass").isPresent()
                && request.queryParam("newPass").isPresent())
            return loginService.updatePassword(request.queryParam("oldPass").get(),
                    request.queryParam("newPass").get(), Integer.valueOf(request.queryParam("loginId").get()))
                    .then(ServerResponse.noContent().build());
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> postLogin(ServerRequest request) {
        if(request.queryParam("email").isPresent()){
            Login login = new Login();
            login.setEmail(request.queryParam("email").get());
            return loginService.postLogin(login)
                    .then(ServerResponse.noContent().build());}
        return ServerResponse.badRequest().build();
    }

    public Mono<ServerResponse> getLoginByEmail(ServerRequest request) {
        if(request.queryParam("email").isPresent())
            return loginService.getLoginByEmail(request.queryParam("email").get())
                    .flatMap(lecturer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(lecturer));
        return ServerResponse.badRequest().build();
    }




}
