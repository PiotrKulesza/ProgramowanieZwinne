package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Lecturer;
import reactor.core.publisher.Mono;

public interface LecturerService {

    Mono<Lecturer> newLecturer(Lecturer lecturer);


}
