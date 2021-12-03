package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student,Integer> {

    @Query("SELECT * FROM student WHERE student_id LIKE %:studentId%")
    Mono<Student> findByStudentId(Integer studentId);

}
