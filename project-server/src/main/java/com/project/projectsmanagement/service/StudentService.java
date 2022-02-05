package com.project.projectsmanagement.service;

import org.springframework.web.bind.annotation.RequestParam;
import com.project.projectsmanagement.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> getStudenci();


    Mono<Student> getStudent(Integer studentId);

    Mono<Void> deleteStudent(Integer studentId, Integer loginId);

    Mono<Student> getStudentByLogin(String email, String pass);

    Mono<Void> postStudent(Student student);

    Mono<Student> getStudentByNrIndeksu(String nrIndeksu);

    Flux<Student> getStudentsByProject(Integer projectId);

    Mono<Student> getStudentInProject(Integer projectId,Integer studentId);

    Mono<Void> updateImie(Integer studentId, String imie);
    Mono<Void> updateNazwisko(Integer studentId, String nazwisko);
    Mono<Void> updateNrIndeksu(Integer studentId, String nrIndeksu);
}
