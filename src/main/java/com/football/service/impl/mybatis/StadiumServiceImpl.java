package com.football.service.impl.mybatis;

import com.football.model.Stadium;
import com.football.mybatis.mapper.StadiumMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.StadiumService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StadiumServiceImpl implements StadiumService {
    @Override
    public int create(Stadium e) {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            StadiumMapper m = session.getMapper(StadiumMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Stadium> list() {
        try (SqlSession session = MyBatisUtil.getFactory().openSession(true)) {
            StadiumMapper m = session.getMapper(StadiumMapper.class);
            return m.findAll();
        }
    }
}
