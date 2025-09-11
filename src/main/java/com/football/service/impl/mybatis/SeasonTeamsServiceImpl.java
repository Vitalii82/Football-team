package com.football.service.impl.mybatis;

import com.football.model.SeasonTeams;
import com.football.mybatis.mapper.SeasonTeamsMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.SeasonTeamsService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SeasonTeamsServiceImpl implements SeasonTeamsService {
    @Override
    public int create(SeasonTeams e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            SeasonTeamsMapper m = session.getMapper(SeasonTeamsMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<SeasonTeams> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            SeasonTeamsMapper m = session.getMapper(SeasonTeamsMapper.class);
            return m.findAll();
        }
    }
}
