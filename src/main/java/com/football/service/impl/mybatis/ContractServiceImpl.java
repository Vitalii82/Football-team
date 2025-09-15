package com.football.service.impl.mybatis;

import com.football.model.Contract;
import com.football.mybatis.mapper.ContractMapper;
import com.football.persistence.MyBatisUtil;
import com.football.service.ContractService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ContractServiceImpl implements ContractService {
    @Override
    public int create(Contract e) {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            ContractMapper m = session.getMapper(ContractMapper.class);
            return m.insert(e);
        }
    }

    @Override
    public List<Contract> list() {
        try (SqlSession session = MyBatisUtil.getInstance().factory().openSession(true)) {
            ContractMapper m = session.getMapper(ContractMapper.class);
            return m.findAll();
        }
    }
}
