package com.project.projectsmanagement.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LoginRouter {

    @Bean
    public RouterFunction<ServerResponse> loginRoute(LoginHandler loginHandler) {
        return RouterFunctions
                .route(PUT("/loginUpdate"),loginHandler::updatePassword)
                .andRoute(GET("/getLoginByEmail"),loginHandler::getLoginByEmail)
                .andRoute(POST("/postLogin"),loginHandler::postLogin);
    }
}
