package com.football.service;

import com.football.model.PlayerStatistics;
import java.util.List;

public interface PlayerStatisticsService {
    int create(PlayerStatistics e);
    List<PlayerStatistics> list();
}
