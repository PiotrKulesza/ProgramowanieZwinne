package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends ReactiveCrudRepository<Lecturer, Integer> {

}
