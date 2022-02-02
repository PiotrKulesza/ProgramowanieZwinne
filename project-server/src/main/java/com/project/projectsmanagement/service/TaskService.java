package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Task;
import reactor.core.publisher.Flux;

public interface TaskService {

    Flux<Task> getTaskByProject(Integer projectId);

}
