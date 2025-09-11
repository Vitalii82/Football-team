package com.football.mybatis.mapper;

import com.football.model.Contract;
import java.util.List;

public interface ContractMapper {
    List<Contract> findAll();
    int insert(Contract e);
}
