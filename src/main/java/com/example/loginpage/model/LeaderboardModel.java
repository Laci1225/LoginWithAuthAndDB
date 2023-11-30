package com.example.loginpage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "leaderboard_table")
@Data
public class LeaderboardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    String login;
    Integer score;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}