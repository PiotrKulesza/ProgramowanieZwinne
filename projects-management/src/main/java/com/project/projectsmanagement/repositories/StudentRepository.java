package com.project.projectsmanagement.repositories;

import com.project.projectsmanagement.model.Student;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Maybe<Student> findByStudentId(Integer studentId);

}
