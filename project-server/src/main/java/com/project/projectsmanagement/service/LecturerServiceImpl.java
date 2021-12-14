package com.project.projectsmanagement.service;

import org.springframework.stereotype.Service;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.repositories.LecturerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LecturerServiceImpl implements LecturerService {

	private final LecturerRepository lecturerRepository;

	public LecturerServiceImpl(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
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


}
