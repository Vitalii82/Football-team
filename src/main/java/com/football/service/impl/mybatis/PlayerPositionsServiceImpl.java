package com.football.service.impl.mybatis;

import com.football.model.PlayerPositions;
import com.football.mybatis.mapper.PlayerPositionsMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.PlayerPositionsService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PlayerPositionsServiceImpl implements PlayerPositionsService {
    @Override
    public int create(PlayerPositions e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            PlayerPositionsMapper m = session.getMapper(PlayerPositionsMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<PlayerPositions> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            PlayerPositionsMapper m = session.getMapper(PlayerPositionsMapper.class);
            return m.findAll();
        }
    }
}
