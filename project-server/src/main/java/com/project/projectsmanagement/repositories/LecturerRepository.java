package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.projectsmanagement.model.Lecturer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LecturerRepository extends ReactiveCrudRepository<Lecturer, Integer> {

    @Override
    @Query("SELECT lecturer.*, login.login_id AS login_id, login.email AS email FROM lecturer JOIN login ON login.login_id=lecturer.login_id")
    Flux<Lecturer> findAll();

    @Override
    @Query("SELECT lecturer.*, login.login_id AS login_id, login.email AS email FROM lecturer JOIN login ON login.login_id=lecturer.login_id WHERE lecturer.lecturer_id = :lecturerId")
    Mono<Lecturer> findById(Integer lecturerId);

    @Query("SELECT lecturer.*, lecturer.login_id AS login_id, login.email AS email FROM lecturer JOIN login ON login.login_id=lecturer.login_id WHERE login.email = :email AND login.password = :pass")
    Mono<Lecturer> findByLoginEmailAndPassword(String email, String pass);

    @Query("SELECT lecturer.*, lecturer.login_id AS login_id, login.email AS email FROM lecturer JOIN login ON login.login_id=lecturer.login_id WHERE login.nrindeksu = :nrIndeksu")
    Mono<Lecturer> findByNrIndeksu(String nrIndeksu);

}
