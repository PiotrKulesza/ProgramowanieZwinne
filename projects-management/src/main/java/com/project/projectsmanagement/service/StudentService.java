package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Student;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Mono<Student> saveStudent(Student student);

    Mono<Student>  updateStudent(Student student, Integer studentId);

    Mono<Student> getStudent(Integer studentId);

    boolean deleteStudent(@RequestParam Integer studentId);

}
