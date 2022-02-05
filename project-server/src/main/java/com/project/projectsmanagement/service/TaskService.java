package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Flux<Task> getTaskByProject(Integer projectId);

    Mono<Void> postTask(Task task);

    Mono<Task> getTaskById(Integer taskId);

    Mono<Void> putNazwa(Integer taskId, String nazwa);

    Mono<Void> putOpis(Integer taskId, String opis);

    Mono<Void> putKanban(Integer taskId, String kanban);

    Mono<Void> deleteTask(Integer taskId);

    Mono<Void> deleteTaskByProject(Integer projectId);

}
