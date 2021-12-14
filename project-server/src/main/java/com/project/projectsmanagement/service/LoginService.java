package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Login;

import reactor.core.publisher.Mono;

public interface LoginService {
    Mono<Login> getUser(String email, String password);
    Mono<Integer> newUser(Login login);
    Mono<Login> updatePassword(String oldPass, String newPass, Integer loginId);
    Mono<Login> getUserById(Integer login_id);
}
