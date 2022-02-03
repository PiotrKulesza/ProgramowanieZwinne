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


    public LoginServiceImpl(LoginRepository loginRepository, TransactionalOperator transactionalOperator,
                            DatabaseClient databaseClient){
        this.loginRepository=loginRepository;
        this.transactionalOperator = transactionalOperator;
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<Void> updatePassword(String oldPass, String newPass, Integer loginId) {

    	return databaseClient
                .sql("UPDATE login SET password = :newPass WHERE password = :oldPass AND login_id = :loginId")
                .bind("loginId", loginId)
                .bind("newPass", newPass)
                .bind("oldPass", oldPass)
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Void> postLogin(Login login) {
        return databaseClient
                .sql("INSERT INTO login (email, password) VALUES (:email, :password)")
                .bind("email", login.getEmail())
                .bind("password", "Start123!")
                .fetch()
                .rowsUpdated()
                .then().as(transactionalOperator::transactional);
    }

    @Override
    public Mono<Login> getLoginByEmail(String email) {
        return loginRepository.findLoginByEmail(email);
    }


}
