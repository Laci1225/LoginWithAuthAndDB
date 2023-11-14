package com.example.loginpage.controller;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public String register(@RequestBody UsersModel usersModel) {
        System.out.println("Login: " + usersModel);
        var user = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return user == null ? "Error" : "OK";
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersModel usersModel) {
        System.out.println("Login: " + usersModel);
        var userAuthenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        System.out.println(userAuthenticated);
        return userAuthenticated == null ? "Error" : userAuthenticated.getLogin();
    }

}
