package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Student;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student,Integer> {

    @Query("SELECT * FROM student WHERE student_id LIKE %:value%")
    Mono<Student> findByStudentId(Integer studentId);

}
