package com.example.loginpage.controller;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/register")
    public String getRegisterPage() {
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@RequestBody UsersModel usersModel){
        System.out.println("Login: " + usersModel);
        var user = usersService.registerUser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return user == null ? "error_page": "login_page";
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersModel usersModel){
        System.out.println("Login: " + usersModel);
        var userAuthedticated = usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        return userAuthedticated == null ? "error_page": "personal_page";
    }
    @GetMapping("/users")
    public List<UsersModel> allUsers(){
        return usersService.getAllUsers();
    }
}
