package com.example.loginpage.service;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersModel registerUser(String login, String password, String email){
        var users = usersRepository.findAll();
        if (users.stream().map(UsersModel::getLogin).toList().contains(login))
        {
            return null;
        }
        if (login != null && password != null){
            var usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
        return null;
    }
    public UsersModel authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }
}
