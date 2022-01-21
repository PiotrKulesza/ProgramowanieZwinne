package com.project.projectsmanagement.repositories;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.projectsmanagement.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login ON login.login_id=student.login_id WHERE student.student_id = :studentId")
	Mono<Student> findByStudentId(Integer studentId);

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login ON login.login_id=student.login_id")
	@Override
	Flux<Student> findAll();

	@Override
	@Query("DELETE FROM student WHERE student.student_id=:studentId")
	Mono<Void> deleteById(Publisher<Integer> studentId);

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login ON login.login_id=student.login_id WHERE login.email = :email AND login.password = :pass")
	Mono<Student > findByLoginEmailAndPassword(String email, String pass);
}
