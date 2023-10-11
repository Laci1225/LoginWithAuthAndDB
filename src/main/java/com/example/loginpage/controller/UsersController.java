package com.example.loginpage.controller;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest",new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest",new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("Login: " + usersModel);
        var user = usersService.registerUser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return user == null ? "error_page": "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("Login: " + usersModel);
        var userAuthedticated = usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        model.addAttribute("usersLogin", userAuthedticated.getLogin());
        return userAuthedticated == null ? "error_page": "personal_page";
    }
}
