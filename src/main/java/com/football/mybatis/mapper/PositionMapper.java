package com.football.mybatis.mapper;

import com.football.model.Position;
import java.util.List;

public interface PositionMapper {
    List<Position> findAll();
    int insert(Position e);
}
