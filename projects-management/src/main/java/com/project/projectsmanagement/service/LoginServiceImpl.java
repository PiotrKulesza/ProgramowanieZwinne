package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Student;
import com.project.projectsmanagement.repositories.LoginRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;
    private final StudentService studentService;
    private final LecturerService lecturerService;


    public LoginServiceImpl(LoginRepository loginRepository, StudentService studentService
            ,LecturerService lecturerService){
        this.loginRepository=loginRepository;
        this.studentService=studentService;
        this.lecturerService=lecturerService;
    }


    @Override
    public Mono<Login> getUser(String email, String password) {
        Mono<Login> user = loginRepository.findLoginByEmailAndPassword(email,password);
        user.flatMap(login -> {
            login.setPassword("");
            return Mono.just(login);
        });

        return user;
    }

    @Override
    public Integer newUser(Login login) {
        boolean addedStudent=false;
        boolean addedLecturer=false;
        Login loginTest = loginRepository.findLoginByEmail(login.getEmail()).blockOptional().get();

        if(loginTest.getEmail()!=null){

            if(loginTest.getStudent()==null && login.getStudent()!=null){
                Mono<Student> student = studentService.saveStudent(login.getStudent());
                loginTest.setStudent(student.blockOptional().get());
                loginRepository.save(loginTest);
                addedStudent=true;
            }

            if(loginTest.getLecturer()==null && login.getLecturer()!=null){
                Mono<Lecturer> lecturer = lecturerService.newLecturer(login.getLecturer());
                loginTest.setLecturer(lecturer.blockOptional().get());
                loginRepository.save(loginTest);
                addedLecturer=true;
            }


            if(addedStudent && addedLecturer){
                return 0;
            }else if(addedLecturer){
                return 1;
            }else if(addedStudent){
                return 2;
            }else{
                return 3;
            }


        }else{
            if(login.getLecturer()!=null){
                Mono<Lecturer> lecturer = lecturerService.newLecturer(login.getLecturer());
                login.setLecturer(lecturer.blockOptional().get());
                addedLecturer=true;
            }

            if(login.getStudent()!=null){
                Mono<Student> student = studentService.saveStudent(login.getStudent());
                login.setStudent(student.blockOptional().get());
                addedStudent=true;
            }

            if(login.getLecturer()==null && login.getStudent()==null)return 5;

            if(loginRepository.save(login).blockOptional().get().getLoginId() == null){
                return 4;
            }else{
                if(addedStudent && addedLecturer){
                    return 5;
                }else if(addedLecturer){
                    return 6;
                }else if(addedStudent){
                    return 7;
                }else{
                    return 8;
                }
            }
        }
    }

    @Override
    public Integer updatePassword(String oldPass, String newPass, Integer loginId) {

        Login login = loginRepository.findById(loginId).blockOptional().get();

        if(login.getPassword().equals(oldPass)){
            login.setPassword(newPass);
            login = loginRepository.save(login).blockOptional().get();
            if(login.getPassword().equals(newPass)) return 0;
            return 1;
        }else return 2;

    }
}
