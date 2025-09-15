package com.football.service.impl.mybatis;

import com.football.model.Manager;
import com.football.mybatis.mapper.ManagerMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.ManagerService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    @Override
    public int create(Manager e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            ManagerMapper m = session.getMapper(ManagerMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Manager> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            ManagerMapper m = session.getMapper(ManagerMapper.class);
            return m.findAll();
        }
    }
}
