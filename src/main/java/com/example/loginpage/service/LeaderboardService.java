package com.example.loginpage.service;

import com.example.loginpage.model.LeaderboardModel;
import com.example.loginpage.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;
    public List<LeaderboardModel> getAllScore() {
        Sort sortByScoreDesc = Sort.by(Sort.Order.desc("score"));
        return leaderboardRepository.findAll(sortByScoreDesc);
    }

    public LeaderboardModel saveScore(LeaderboardModel leaderboard) {
        LeaderboardModel existingLeaderboard = leaderboardRepository.findByLogin(leaderboard.getLogin());
        if (leaderboard.getLogin() == null || leaderboard.getLogin().isEmpty())
            return null;
        if (existingLeaderboard == null) {
            leaderboard.setCreatedAt(LocalDateTime.now());
            leaderboard.setModifiedAt(LocalDateTime.now());
            return leaderboardRepository.save(leaderboard);
        } else if (leaderboard.getScore() > existingLeaderboard.getScore()) {
            existingLeaderboard.setScore(leaderboard.getScore());
            existingLeaderboard.setModifiedAt(LocalDateTime.now());
            return leaderboardRepository.save(existingLeaderboard);
        }
        return null;
    }

}