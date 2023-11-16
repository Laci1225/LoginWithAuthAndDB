package com.example.loginpage.service;

import com.example.loginpage.model.UsersModel;
import com.example.loginpage.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UsersModel registerUser(String login, String password, String email) {
        var users = usersRepository.findAll();
        if (users.stream().map(UsersModel::getLogin).toList().contains(login)) {
            return null;
        }
        if (login != null && password != null) {
            var usersModel = new UsersModel();
            usersModel.setLogin(login);

            String hashedPassword = passwordEncoder.encode(password);
            usersModel.setPassword(hashedPassword);

            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
        return null;
    }

    public UsersModel authenticate(String login, String password) {
        UsersModel user = usersRepository.findByLogin(login).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }
}
