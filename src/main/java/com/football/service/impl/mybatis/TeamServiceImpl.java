package com.football.service.impl.mybatis;

import com.football.model.Team;
import com.football.mybatis.mapper.TeamMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.TeamService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TeamServiceImpl implements TeamService {
    @Override
    public int create(Team e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TeamMapper m = session.getMapper(TeamMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Team> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TeamMapper m = session.getMapper(TeamMapper.class);
            return m.findAll();
        }
    }
}
