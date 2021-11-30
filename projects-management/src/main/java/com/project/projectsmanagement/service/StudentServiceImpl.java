package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.StudentRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Override
    public Mono<Student> saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> updateStudent(Student student, Integer studentId) {

        Mono<Student> studentDB=studentRepository.findByStudentId(studentId);
        studentDB.map(s->{
            if(student.getImie()!=null)s.setImie(student.getImie());
            if(student.getNazwisko()!=null)s.setNazwisko(student.getNazwisko());
            if(student.getNrIndeksu()!=null)s.setNrIndeksu(student.getNrIndeksu());
            if(student.isStacjonarny()!=s.isStacjonarny())s.setStacjonarny(student.isStacjonarny());
            return s;
        });

        return studentRepository.save(studentDB.blockOptional().get());
    }

    @Override
    public Mono<Student> getStudent(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public boolean deleteStudent(Integer studentId) {
        return false;
    }


}
