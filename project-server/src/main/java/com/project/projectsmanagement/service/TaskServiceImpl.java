package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Task;
import com.project.projectsmanagement.repositories.TaskRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TransactionalOperator transactionalOperator;
    private final DatabaseClient databaseClient;

    public TaskServiceImpl(TaskRepository taskRepository, TransactionalOperator transactionalOperator, DatabaseClient databaseClient) {
        this.taskRepository = taskRepository;
        this.transactionalOperator = transactionalOperator;
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<Task> getTaskByProject(Integer projectId) {
        return taskRepository.findByProject(projectId);
    }
}
