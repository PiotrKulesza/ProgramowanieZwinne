package com.project.projectsmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.LoginRepository;
import com.project.projectsmanagement.repositories.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final TransactionalOperator transactionalOperator;
	private final DatabaseClient databaseClient;


	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, TransactionalOperator transactionalOperator, DatabaseClient databaseClient) {
		this.studentRepository = studentRepository;

		this.transactionalOperator = transactionalOperator;
		this.databaseClient = databaseClient;
	}


	@Override
	public Mono<Student> getStudent(Integer studentId) {

		return studentRepository.findByStudentId(studentId);
	}

	@Override
	public Mono<Void> deleteStudent(Integer studentId, Integer loginId) {

		return databaseClient
				.sql("DELETE FROM project_student WHERE project_student.student_id = :studentId")
				.bind("studentId",studentId)
				.fetch().rowsUpdated()
				.then().as(transactionalOperator::transactional)
				.then(databaseClient
						.sql("DELETE FROM student WHERE student.student_id = :studentId")
						.bind("studentId",studentId)
						.fetch().rowsUpdated()
						.then().as(transactionalOperator::transactional))
				.then(databaseClient
						.sql("DELETE FROM login WHERE login.login_id = :loginId")
						.bind("loginId",loginId)
						.fetch().rowsUpdated()
						.then().as(transactionalOperator::transactional));


	}

	@Override
	public Mono<Student> getStudentByLogin(String email, String pass) {
		return studentRepository.findByLoginEmailAndPassword(email,pass);
	}

	@Override
	public Mono<Void> postStudent(Student student) {
		return databaseClient
				.sql("INSERT INTO student (imie, nazwisko, nrindeksu, login_id) VALUES " +
						"(:imie, :nazwisko, :nrIndeksu, :loginId)")
				.bind("imie", student.getImie())
				.bind("nazwisko", student.getNazwisko())
				.bind("nrIndeksu", student.getNrIndeksu())
				.bind("loginId", student.getLogin().getLoginId())
				.fetch()
				.rowsUpdated()
				.then().as(transactionalOperator::transactional);
	}

	@Override
	public Mono<Student> getStudentByNrIndeksu(String nrIndeksu) {
		return studentRepository.findByNrIndeksu(nrIndeksu);
	}

	@Override
	public Flux<Student> getStudentsByProject(Integer projectId) {
		return studentRepository.findByProject(projectId);
	}

	@Override
	public Mono<Student> getStudentInProject(Integer projectId, Integer studentId) {
		return studentRepository.findInProject(projectId,studentId);
	}

	@Override
	public Mono<Void> updateImie(Integer studentId, String imie) {
		return databaseClient
				.sql("UPDATE student SET imie = :imie WHERE student_id = :studentId")
				.bind("studentId", studentId)
				.bind("imie", imie)
				.fetch()
				.rowsUpdated()
				.then().as(transactionalOperator::transactional);
	}

	@Override
	public Mono<Void> updateNazwisko(Integer studentId, String nazwisko) {
		return databaseClient
				.sql("UPDATE student SET nazwisko = :nazwisko WHERE student_id = :studentId")
				.bind("studentId", studentId)
				.bind("nazwisko", nazwisko)
				.fetch()
				.rowsUpdated()
				.then().as(transactionalOperator::transactional);
	}

	@Override
	public Mono<Void> updateNrIndeksu(Integer studentId, String nrIndeksu) {
		return databaseClient
				.sql("UPDATE student SET nrindeksu = :nrIndeksu WHERE student_id = :studentId")
				.bind("studentId", studentId)
				.bind("nrIndeksu", nrIndeksu)
				.fetch()
				.rowsUpdated()
				.then().as(transactionalOperator::transactional);
	}

	@Override
	public Flux<Student> getStudenci() {
		return studentRepository.findAll();
	}

}
