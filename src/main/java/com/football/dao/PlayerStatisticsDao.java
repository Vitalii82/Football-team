package com.football.dao;

import com.football.model.PlayerStatistics;
import java.util.List;

public interface PlayerStatisticsDao {
    int insert(PlayerStatistics e);
    List<PlayerStatistics> findAll();
}
