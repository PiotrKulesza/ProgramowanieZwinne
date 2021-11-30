package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.StudentRepository;
import com.project.projectsmanagement.service.StudentService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*@PutMapping(value = "/student/updateStudent", params = {"studentId"})
    public void updateStudent(@RequestBody Student student, @RequestParam Integer studentId){
        studentService.updateStudent(student,studentId);
    }

    @GetMapping(value = "/student/getStudent", params = {"studentId"})
    public Maybe<Student> getStudent(@RequestParam Integer studentId){
        return studentService.getStudent(studentId);
    }

    @DeleteMapping(value = "/student/deleteStudent", params = {"studentId"})
    public boolean deleteStudent(@RequestParam Integer studentId){
        return studentService.getStudent(studentId);
    }*/
}
