package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.StudentRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student, Integer studentId) {

        Student studentDB=studentRepository.getById(studentId);
        if(student.getImie()!=null)studentDB.setImie(student.getImie());
        if(student.getNazwisko()!=null)studentDB.setNazwisko(student.getNazwisko());
        if(student.getNrIndeksu()!=null)studentDB.setNrIndeksu(student.getNrIndeksu());
        if(student.isStacjonarny()!=studentDB.isStacjonarny())studentDB.setStacjonarny(student.isStacjonarny());
        studentRepository.save(studentDB);
    }

    @Override
    public Maybe<Student> getStudent(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public boolean deleteStudent(Integer studentId) {
         studentRepository.deleteById(studentId);
         boolean result;
         return studentRepository.findById(studentId).isPresent();
    }


}
