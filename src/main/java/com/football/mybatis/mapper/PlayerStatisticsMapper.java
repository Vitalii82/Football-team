package com.football.mybatis.mapper;

import com.football.model.PlayerStatistics;
import java.util.List;

public interface PlayerStatisticsMapper {
    List<PlayerStatistics> findAll();
    int insert(PlayerStatistics e);
}
