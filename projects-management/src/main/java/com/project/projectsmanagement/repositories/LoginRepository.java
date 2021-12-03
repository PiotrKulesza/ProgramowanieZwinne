package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Login;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LoginRepository extends ReactiveCrudRepository<Login,Integer> {


    Mono<Login> findLoginByEmailAndPassword(String email, String password);
    Mono<Login> findLoginByEmail(String email);


}
