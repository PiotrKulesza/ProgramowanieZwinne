package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LecturerService {


    Flux<Lecturer> findAll();

    Mono<Lecturer> getLecturer(Integer lecturerId);

    Mono<Lecturer> getLecturerByLogin(String email, String pass);

    Mono<Void> updateImie(Integer lecturerId, String imie);
    Mono<Void> updateNazwisko(Integer lecturerId, String imie);

    Mono<Void> postLecturer(Lecturer lecturer);


}
