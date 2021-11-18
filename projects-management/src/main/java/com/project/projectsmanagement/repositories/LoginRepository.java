package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Login;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Integer> {

    Maybe<Login> findLoginByEmailAndPassword(String email, String password);
    Login findLoginByEmail(String email);


}
