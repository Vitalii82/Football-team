package com.football.mybatis.mapper;

import com.football.model.PlayerPositions;
import java.util.List;

public interface PlayerPositionsMapper {
    List<PlayerPositions> findAll();
    int insert(PlayerPositions e);
}
