package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Task;
import com.project.projectsmanagement.repositories.TaskRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<Void> postTask(Task task) {

        return databaseClient
                .sql("INSERT INTO task (nazwa, opis, kanban, project_id) VALUES " +
                        "(:nazwa, :opis, :kanban, :projectId)")
                .bind("nazwa", task.getNazwa())
                .bind("opis", task.getOpis())
                .bind("kanban", "NEW")
                .bind("projectId", task.getProject().getProjectId())
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Task> getTaskById(Integer taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    @Override
    public Mono<Void> putNazwa(Integer taskId, String nazwa) {
        return databaseClient
                .sql("UPDATE task SET nazwa = :nazwa WHERE task_id = :taskId")
                .bind("nazwa", nazwa)
                .bind("taskId", taskId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> putOpis(Integer taskId, String opis) {
        return databaseClient
                .sql("UPDATE task SET opis = :opis WHERE task_id = :taskId")
                .bind("opis", opis)
                .bind("taskId", taskId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> putKanban(Integer taskId, String kanban) {
        return databaseClient
                .sql("UPDATE task SET kanban = :kanban WHERE task_id = :taskId")
                .bind("kanban", kanban)
                .bind("taskId", taskId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> deleteTask(Integer taskId) {
        return databaseClient
                .sql("DELETE FROM task WHERE task.task_id=:taskId")
                .bind("taskId", taskId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> deleteTaskByProject(Integer projectId) {
        return databaseClient
                .sql("DELETE FROM task WHERE task.project_id=:projectId")
                .bind("projectId", projectId)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }


}
