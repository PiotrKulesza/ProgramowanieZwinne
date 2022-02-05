package com.project.projectsmanagement.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.projectsmanagement.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login " +
			"ON login.login_id=student.login_id " +
			"WHERE student.student_id = :studentId ORDER BY student.student_id")
	Mono<Student> findByStudentId(Integer studentId);

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login " +
			"ON login.login_id=student.login_id ORDER BY student.student_id")
	@Override
	Flux<Student> findAll();

	@Query("SELECT student.*, login.login_id AS login_id, login.email AS email FROM student JOIN login " +
			"ON login.login_id=student.login_id WHERE login.email = :email " +
			"AND login.password = :pass  ORDER BY student.student_id")
	Mono<Student > findByLoginEmailAndPassword(String email, String pass);

	@Query("SELECT student.*, student.login_id AS login_id, login.email AS email " +
			"FROM student JOIN login ON login.login_id=student.login_id WHERE student.nrindeksu = :nrIndeksu")
	Mono<Student> findByNrIndeksu(String nrIndeksu);

	@Query("SELECT student.*, student.login_id AS login_id, login.email AS email " +
			"FROM student JOIN login ON login.login_id=student.login_id JOIN project_student ON " +
			"project_student.student_id=student.student_id WHERE project_student.project_id = :projectId ORDER BY student.student_id")
	Flux<Student> findByProject(Integer projectId);

	@Query("SELECT student.*, student.login_id AS login_id, login.email AS email " +
			"FROM student JOIN login ON login.login_id=student.login_id JOIN project_student ON " +
			"project_student.student_id=student.student_id WHERE project_student.project_id = :projectId " +
			"AND project_student.student_id = :studentId")
	Mono<Student>findInProject(Integer projectId,Integer studentId);


}
