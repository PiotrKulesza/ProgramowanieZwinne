package com.project.projectsmanagement.service;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.repositories.LecturerRepository;

import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LecturerServiceImpl implements LecturerService {

	private final LecturerRepository lecturerRepository;
	private final TransactionalOperator transactionalOperator;
	private final DatabaseClient databaseClient;

	public LecturerServiceImpl(LecturerRepository lecturerRepository, TransactionalOperator transactionalOperator, DatabaseClient databaseClient) {
		this.lecturerRepository = lecturerRepository;
		this.transactionalOperator = transactionalOperator;
		this.databaseClient = databaseClient;
	}

	@Override
	public Mono<Lecturer> newLecturer(Lecturer lecturer) {
		return lecturerRepository.save(lecturer);
	}

	@Override
	public Flux<Lecturer> findAll() {
		return lecturerRepository.findAll();
	}

	@Override
	public Mono<Lecturer> getLecturer(Integer lecturerId) {
		return lecturerRepository.findById(lecturerId);
	}

	@Override
	public Mono<Lecturer> getLecturerByLogin(String email, String pass) {
		return lecturerRepository.findByLoginEmailAndPassword(email,pass);
	}

	@Override
	public Mono<Void> updateImie(Integer lecturerId, String imie) {
		System.out.println(lecturerId);
		System.out.println(imie);
		Mono<Lecturer> lecturer = lecturerRepository.findById(lecturerId);
		lecturer.flatMap(lecturer1 -> {
			lecturer1.setImie(imie);
			lecturerRepository.save(lecturer1);
			return Mono.just(lecturer1);
		});
		return databaseClient
				.sql("UPDATE lecturer SET imie = :imie WHERE lecturer_id = :lecturerId")
				.bind("lecturerId",lecturerId)
				.bind("imie",imie)
				.fetch().rowsUpdated().then();
				//.then().as(transactionalOperator::transactional);
	}


}
