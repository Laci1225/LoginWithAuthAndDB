package com.example.loginpage.controller;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RestController
@RequestMapping
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    private final WebClient webClient;

    @Autowired
    public UsersController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<UsersModel> getData(String name) {
        return webClient.get()
                .uri("/{name}", name)
                .retrieve()
                .bodyToMono(UsersModel.class);
    }


    @GetMapping("/register")
    public String getRegisterPage() {
        return "register_page";
    }

    @GetMapping("-")
    public String getLoginPage() {
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@RequestBody UsersModel usersModel) {
        System.out.println("Login: " + usersModel);
        var user = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return user == null ? "Error" : user.getLogin();
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersModel usersModel) {
        System.out.println("Login: " + usersModel);
        var userAuthenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        System.out.println(userAuthenticated);
        return userAuthenticated == null ? "Error" : userAuthenticated.getLogin();
    }

    @GetMapping("/users")
    public List<UsersModel> allUsers() {
        return usersService.getAllUsers();
    }
}
