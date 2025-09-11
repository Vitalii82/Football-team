package com.football.service.impl.mybatis;

import com.football.model.PlayerStatistics;
import com.football.mybatis.mapper.PlayerStatisticsMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.PlayerStatisticsService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PlayerStatisticsServiceImpl implements PlayerStatisticsService {
    @Override
    public int create(PlayerStatistics e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            PlayerStatisticsMapper m = session.getMapper(PlayerStatisticsMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<PlayerStatistics> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            PlayerStatisticsMapper m = session.getMapper(PlayerStatisticsMapper.class);
            return m.findAll();
        }
    }
}
