package com.football.service.impl.mybatis;

import com.football.model.Matche;
import com.football.mybatis.mapper.MatcheMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.MatcheService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MatcheServiceImpl implements MatcheService {
    @Override
    public int create(Matche e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            MatcheMapper m = session.getMapper(MatcheMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Matche> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            MatcheMapper m = session.getMapper(MatcheMapper.class);
            return m.findAll();
        }
    }
}
