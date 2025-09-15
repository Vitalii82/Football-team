package com.football.service.impl.mybatis;

import com.football.model.Position;
import com.football.mybatis.mapper.PositionMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.PositionService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    @Override
    public int create(Position e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            PositionMapper m = session.getMapper(PositionMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Position> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            PositionMapper m = session.getMapper(PositionMapper.class);
            return m.findAll();
        }
    }
}
