package com.football.service.impl.jdbc;

import com.football.model.PlayerStatistics;
import com.football.service.PlayerStatisticsService;
import com.football.service.impl.mybatis.PlayerStatisticsServiceImpl;

import java.util.List;

// Stub JDBC service mirroring signature; delegates to MyBatis to keep example compact.
// In your real project, this would use JDBC DAOs.
public class PlayerStatisticsServiceImpl implements PlayerStatisticsService {
    private final PlayerStatisticsServiceImpl delegate = new PlayerStatisticsServiceImpl();

    @Override
    public int create(PlayerStatistics e) { return delegate.create(e); }

    @Override
    public List<PlayerStatistics> list() { return delegate.list(); }
}
