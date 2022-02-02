package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Project;
import com.project.projectsmanagement.repositories.ProjectRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final TransactionalOperator transactionalOperator;
    private final DatabaseClient databaseClient;

    public ProjectServiceImpl(ProjectRepository projectRepository, TransactionalOperator transactionalOperator, DatabaseClient databaseClient) {
        this.projectRepository = projectRepository;
        this.transactionalOperator = transactionalOperator;
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<Project> getProjectsByLecturer(Integer lecturerId) {
        return projectRepository.findByLecturer(lecturerId);
    }

    @Override
    public Mono<Void> postProject(String nazwa, String opis, Integer lecturerId) {
        return databaseClient
                .sql("INSERT INTO project(lecturer_id, nazwa, opis, dataczasutworzenia) VALUES " +
                        "(:lecturerId, :nazwa, :opis, :dataczasutworzenia)")
                .bind("nazwa", nazwa)
                .bind("opis", opis)
                .bind("lecturerId", lecturerId)
                .bind("dataczasutworzenia", LocalDate.now())
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> addStudentToProject(Integer projectId, Integer studentId) {
        System.out.println("TEST");
        return databaseClient
                .sql("INSERT INTO project_student(student_id, project_id) VALUES (:studentId , :projectId)")
                .bind("studentId", studentId)
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }
}
