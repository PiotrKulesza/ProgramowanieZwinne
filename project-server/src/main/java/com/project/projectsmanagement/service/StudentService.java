package com.project.projectsmanagement.service;

import org.springframework.web.bind.annotation.RequestParam;
import com.project.projectsmanagement.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
	Flux<Student> getStudenci();
	
    Mono<Student> saveStudent(Student student);

    Mono<Student>  updateStudent(Student student, Integer studentId);

    Mono<Student> getStudent(Integer studentId);

    Mono<Void> deleteStudent(Integer studentId);

    Mono<Student> getStudentByLogin(String email, String pass);

}
