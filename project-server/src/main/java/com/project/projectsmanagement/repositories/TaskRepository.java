package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.model.Task;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveCrudRepository<Task, Integer> {

    @Query("SELECT task.* FROM task WHERE task.project_id=:projectId")
    Flux<Task> findByProject(Integer projectId);

}
