package com.example.loginpage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users_table")
@Data
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String email;
    private String password;
}
