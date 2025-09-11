package com.football.mybatis.mapper;

import com.football.model.Player;
import java.util.List;

public interface PlayerMapper {
    List<Player> findAll();
    int insert(Player e);
}
