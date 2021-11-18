package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Student;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.web.bind.annotation.RequestParam;

public interface StudentService {

    Student saveStudent(Student student);

    void updateStudent(Student student, Integer studentId);

    Maybe<Student> getStudent(Integer studentId);

    boolean deleteStudent(@RequestParam Integer studentId)

}
