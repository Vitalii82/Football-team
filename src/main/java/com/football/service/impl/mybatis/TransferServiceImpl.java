package com.football.service.impl.mybatis;

import com.football.model.Transfer;
import com.football.mybatis.mapper.TransferMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.TransferService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TransferServiceImpl implements TransferService {
    @Override
    public int create(Transfer e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TransferMapper m = session.getMapper(TransferMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Transfer> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            TransferMapper m = session.getMapper(TransferMapper.class);
            return m.findAll();
        }
    }
}
