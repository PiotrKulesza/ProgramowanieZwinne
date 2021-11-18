package com.project.projectsmanagement.controller;

import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.service.LoginService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class LoginRestController {

    private LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping( value = "/single")
    public Maybe<Login> single() {
        Login login = new Login();
        login.setLoginId(1);
        login.setEmail("test");
        login.setPassword("test");
        Maybe<Login> single = Maybe.just(login);
        return single.flatMap(x->{
            x.setPassword("");
            return Maybe.just(x);
        });
    }

    @PostMapping( value = "/login/newUser")
    public Integer newUser(@RequestBody Login login){

        return loginService.newUser(login);

    }

    @GetMapping( value = "/getUser", params={"email","password"})
    public Maybe<Login> getUser(@RequestParam String email, @RequestParam String password){
        return loginService.getUser(email,password);
    }

    @PutMapping(value = "/login/updatePassword")
    public Integer updatePassword(@RequestParam String oldPass, @RequestParam String newPass
            , @RequestParam Integer loginId){

        return loginService.updatePassword(oldPass,newPass,loginId);

    }



}
