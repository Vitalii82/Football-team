package com.football.mybatis.mapper;

import com.football.model.Manager;
import java.util.List;

public interface ManagerMapper {
    List<Manager> findAll();
    int insert(Manager e);
}
