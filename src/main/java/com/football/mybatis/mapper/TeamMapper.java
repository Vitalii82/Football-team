package com.football.mybatis.mapper;

import com.football.model.Team;
import java.util.List;

public interface TeamMapper {
    List<Team> findAll();
    int insert(Team e);
}
