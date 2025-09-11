package com.football.mybatis.mapper;

import com.football.model.Stadium;
import java.util.List;

public interface StadiumMapper {
    List<Stadium> findAll();
    int insert(Stadium e);
}
