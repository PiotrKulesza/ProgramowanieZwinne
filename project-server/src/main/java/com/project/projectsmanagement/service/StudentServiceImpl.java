package com.project.projectsmanagement.service;

import com.project.projectsmanagement.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.StudentRepository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final LoginRepository loginRepository;
	private final TransactionalOperator transactionalOperator;
	private final DatabaseClient databaseClient;


	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, LoginRepository loginRepository
			, TransactionalOperator transactionalOperator, DatabaseClient databaseClient) {
		this.studentRepository = studentRepository;
		this.loginRepository = loginRepository;
		this.transactionalOperator = transactionalOperator;
		this.databaseClient = databaseClient;
	}

	@Override
	public Mono<Student> saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Mono<Student> updateStudent(Student student, Integer studentId) {

		Mono<Student> studentDB = studentRepository.findByStudentId(studentId);
		studentDB.map(s -> {
			if (student.getImie() != null)
				s.setImie(student.getImie());
			if (student.getNazwisko() != null)
				s.setNazwisko(student.getNazwisko());
			if (student.getNrIndeksu() != null)
				s.setNrIndeksu(student.getNrIndeksu());
			if (student.isStacjonarny() != s.isStacjonarny())
				s.setStacjonarny(student.isStacjonarny());
			return s;
		});

		return studentRepository.save(studentDB.blockOptional().get());
	}

	@Override
	public Mono<Student> getStudent(Integer studentId) {

		return studentRepository.findByStudentId(studentId);
	}

	@Override

	public Mono<Void> deleteStudent(final Integer studentId) {

		return databaseClient
				.sql("DELETE FROM student WHERE student_id = :studentId")
				.bind("studentId",studentId)
				.fetch().rowsUpdated()
				.then().as(transactionalOperator::transactional);
	}

	@Override
	public Mono<Student> getStudentByLogin(String email, String pass) {
		System.out.println("Coś sie dzieje");
		return studentRepository.findByLoginEmailAndPassword(email,pass);
	}

	@Override
	public Flux<Student> getStudenci() {
		return studentRepository.findAll();
	}



}
