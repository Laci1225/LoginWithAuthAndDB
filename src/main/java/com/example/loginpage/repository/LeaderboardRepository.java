package com.example.loginpage.repository;

import com.example.loginpage.model.LeaderboardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<LeaderboardModel,Integer> {
}
