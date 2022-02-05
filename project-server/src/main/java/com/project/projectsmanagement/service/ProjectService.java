package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Project;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectService {

    Flux<Project> getProjectsByLecturer(Integer lecturerId);

    Flux<Project> getProjectsByStudent(Integer studentId);

    Mono<Void> postProject(String nazwa, String opis, Integer lecturerId);

    Mono<Void> addStudentToProject(Integer projectId, Integer studentId);

    Mono<Project> getProjectsByProjectId(Integer projectId);

    Mono<Void> putNazwa(Integer projectId, String nazwa);

    Mono<Void> putOpis(Integer projectId, String opis);

    Mono<Void> putZakoncz(Integer projectId);

    Mono<Void> deleteProject(Integer projectId);

    Mono<Void> deleteStudentFromProject(Integer projectId, Integer studentId);

}
