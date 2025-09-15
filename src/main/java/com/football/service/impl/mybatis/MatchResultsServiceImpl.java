package com.football.service.impl.mybatis;

import com.football.model.MatchResults;
import com.football.mybatis.mapper.MatchResultsMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.MatchResultsService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MatchResultsServiceImpl implements MatchResultsService {
    @Override
    public int create(MatchResults e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            MatchResultsMapper m = session.getMapper(MatchResultsMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<MatchResults> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            MatchResultsMapper m = session.getMapper(MatchResultsMapper.class);
            return m.findAll();
        }
    }
}
