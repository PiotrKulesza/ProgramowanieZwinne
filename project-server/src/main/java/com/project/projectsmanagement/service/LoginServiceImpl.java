package com.project.projectsmanagement.service;

import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.repositories.LoginRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Service
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;
    private final TransactionalOperator transactionalOperator;
    private final DatabaseClient databaseClient;
    private final StudentService studentService;
    private final LecturerService lecturerService;


    public LoginServiceImpl(LoginRepository loginRepository, TransactionalOperator transactionalOperator,
                            DatabaseClient databaseClient, StudentService studentService,
                            LecturerService lecturerService){
        this.loginRepository=loginRepository;
        this.transactionalOperator = transactionalOperator;
        this.databaseClient = databaseClient;
        this.studentService=studentService;
        this.lecturerService=lecturerService;
    }


    @Override
    public Mono<Login> getUser(String email, String password) {
        return loginRepository.findLoginByEmailAndPassword(email,password).flatMap(login -> {
            login.setPassword("");
            return Mono.just(login);
        });
    }



    @Override
    public Mono<Integer> newUser(Login login) {
    	/*
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
        */
    	return null;
    }

    @Override
    public Mono<Login> updatePassword(String oldPass, String newPass, Integer loginId) {

    	return loginRepository
    			.findById(loginId).filter(login -> login.getPassword().equals(oldPass))
                .flatMap(login -> {
                    login.setPassword(newPass);
                    Mono<Login> newLogin = loginRepository.save(login);
                    return newLogin;
                });
    }

    @Override
    public Mono<Login> getUserById(Integer login_id) {
        return loginRepository.findById(login_id).flatMap(login ->
        {login.setPassword("");
        return Mono.just(login);});
    }
}
