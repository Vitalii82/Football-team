package com.football.service.impl.mybatis;

import com.football.model.Season;
import com.football.mybatis.mapper.SeasonMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.SeasonService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SeasonServiceImpl implements SeasonService {
    @Override
    public int create(Season e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            SeasonMapper m = session.getMapper(SeasonMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Season> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            SeasonMapper m = session.getMapper(SeasonMapper.class);
            return m.findAll();
        }
    }
}
