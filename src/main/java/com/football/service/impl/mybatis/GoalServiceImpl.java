package com.football.service.impl.mybatis;

import com.football.model.Goal;
import com.football.mybatis.mapper.GoalMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.GoalService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GoalServiceImpl implements GoalService {
    @Override
    public int create(Goal e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            GoalMapper m = session.getMapper(GoalMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Goal> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            GoalMapper m = session.getMapper(GoalMapper.class);
            return m.findAll();
        }
    }
}
