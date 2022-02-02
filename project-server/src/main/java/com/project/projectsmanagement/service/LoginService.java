package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Login;

import reactor.core.publisher.Mono;

public interface LoginService {

    Mono<Void> updatePassword(String oldPass, String newPass, Integer loginId);

    Mono<Void> postLogin(Login login);

    Mono<Login> getLoginByEmail(String email);
}
