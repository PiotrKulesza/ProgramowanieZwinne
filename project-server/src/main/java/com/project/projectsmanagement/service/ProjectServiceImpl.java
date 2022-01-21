package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Project;
import com.project.projectsmanagement.repositories.ProjectRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

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
        projectRepository.findByLecturer(lecturerId).flatMap(project -> {
            System.out.println(project.getNazwa());
            return null;
        });
        return projectRepository.findByLecturer(lecturerId);
    }
}
