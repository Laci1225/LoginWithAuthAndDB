package com.example.loginpage.service;

import com.example.loginpage.model.LeaderboardModel;
import com.example.loginpage.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;
    public List<LeaderboardModel> getAllScore() {
        return leaderboardRepository.findAll();
    }

    public LeaderboardModel saveScore(LeaderboardModel leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }
}
