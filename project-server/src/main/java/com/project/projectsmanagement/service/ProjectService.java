package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Project;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectService {

    Flux<Project> getProjectsByLecturer(Integer lecturerId);

    Mono<Void> postProject(String nazwa, String opis, Integer lecturerId);

    Mono<Void> addStudentToProject(Integer projectId, Integer studentId);



}
