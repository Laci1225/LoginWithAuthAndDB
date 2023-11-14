package com.example.loginpage.controller;

import com.example.loginpage.model.LeaderboardModel;
import com.example.loginpage.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping
@CrossOrigin
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/leaderboard")
    public List<LeaderboardModel> getAllScore(){
        return leaderboardService.getAllScore();
    }
    @PostMapping("/leaderboard")
    public LeaderboardModel saveScore(@RequestBody LeaderboardModel leaderboard){
        return leaderboardService.saveScore(leaderboard);
    }

}
