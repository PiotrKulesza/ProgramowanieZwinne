package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Lecturer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LecturerService {

    Mono<Lecturer> newLecturer(Lecturer lecturer);

    Flux<Lecturer> findAll();

    Mono<Lecturer> getLecturer(Integer lecturerId);


}
