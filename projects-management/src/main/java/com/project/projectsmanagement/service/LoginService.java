package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Login;
import io.reactivex.rxjava3.core.Maybe;
import reactor.core.publisher.Mono;

public interface LoginService {
    Mono<Login> getUser(String email, String password);
    Integer newUser(Login login);
    Integer updatePassword(String oldPass, String newPass, Integer loginId);
}
