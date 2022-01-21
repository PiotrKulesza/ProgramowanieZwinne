package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Project;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProjectRepository extends ReactiveCrudRepository<Project, Integer> {

    @Query("SELECT project.*, lecturer.* FROM project JOIN lecturer ON project.lecturer_id=lecturer.lecturer_id WHERE lecturer.lecturer_id = :lecturerId")
    Flux<Project> findByLecturer(Integer lecturerId);

}
