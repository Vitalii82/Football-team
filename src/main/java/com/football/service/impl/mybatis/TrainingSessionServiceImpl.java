package com.football.service.impl.mybatis;

import com.football.model.TrainingSession;
import com.football.mybatis.mapper.TrainingSessionMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.TrainingSessionService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TrainingSessionServiceImpl implements TrainingSessionService {
    @Override
    public int create(TrainingSession e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TrainingSessionMapper m = session.getMapper(TrainingSessionMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<TrainingSession> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TrainingSessionMapper m = session.getMapper(TrainingSessionMapper.class);
            return m.findAll();
        }
    }
}
