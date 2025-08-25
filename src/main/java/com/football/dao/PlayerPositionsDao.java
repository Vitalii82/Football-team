package com.football.dao;

import com.football.model.PlayerPositions;
import java.util.List;

public interface PlayerPositionsDao {
    int insert(PlayerPositions e);
    List<PlayerPositions> findAll();
}
