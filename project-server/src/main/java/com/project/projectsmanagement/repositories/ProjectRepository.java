package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Project;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectRepository extends ReactiveCrudRepository<Project, Integer> {

    @Query("SELECT project.* FROM project WHERE " +
            "project.lecturer_id = :lecturerId ORDER BY project.project_id")
    Flux<Project> findByLecturer(Integer lecturerId);

    @Query("SELECT project.* FROM project JOIN project_student ON project.project_id=project_student.project_id WHERE " +
            "project_student.student_id = :studentId ORDER BY project.project_id")
    Flux<Project> findByStudent(Integer studentId);

    @Query("SELECT project.* FROM project WHERE project.project_id = :projectId")
    Mono<Project> findByProjectId(Integer projectId);
}
