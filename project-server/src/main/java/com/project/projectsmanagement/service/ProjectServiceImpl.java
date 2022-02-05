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
    public Flux<Project> getProjectsByStudent(Integer studentId) {
        return projectRepository.findByStudent(studentId);
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

        return databaseClient
                .sql("INSERT INTO project_student(student_id, project_id) VALUES (:studentId , :projectId)")
                .bind("studentId", studentId)
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Project> getProjectsByProjectId(Integer projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    @Override
    public Mono<Void> putNazwa(Integer projectId, String nazwa) {
        return databaseClient
                .sql("UPDATE project SET nazwa = :nazwa, dataczasedycji = :dataczasedycji WHERE project_id = :projectId")
                .bind("dataczasedycji", LocalDate.now())
                .bind("nazwa", nazwa)
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> putOpis(Integer projectId, String opis) {
        return databaseClient
                .sql("UPDATE project SET opis = :opis, dataczasedycji = :dataczasedycji WHERE project_id = :projectId")
                .bind("dataczasedycji", LocalDate.now())
                .bind("opis", opis)
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> putZakoncz(Integer projectId) {
        return databaseClient
                .sql("UPDATE project SET dataoddania = :dataoddania WHERE project_id = :projectId")
                .bind("dataoddania", LocalDate.now())
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> deleteProject(Integer projectId) {
        return databaseClient
                .sql("DELETE FROM task WHERE task.project_id=:projectId")
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional)
                .then(databaseClient
                        .sql("DELETE FROM project_student WHERE project_student.project_id=:projectId")
                        .bind("projectId", projectId)
                        .fetch()
                        .rowsUpdated()
                        .then().as(transactionalOperator::transactional)
                ).then(databaseClient
                        .sql("DELETE FROM project WHERE project.project_id=:projectId")
                        .bind("projectId", projectId)
                        .fetch()
                        .rowsUpdated()
                        .then().as(transactionalOperator::transactional)
                );
    }

    @Override
    public Mono<Void> deleteStudentFromProject(Integer projectId, Integer studentId) {
        return databaseClient
                .sql("DELETE FROM project_student WHERE project_student.project_id=:projectId " +
                        "AND project_student.STUDENT_id=:studentId")
                .bind("studentId", studentId)
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }


}
