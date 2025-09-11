package com.football.mybatis.mapper;

import com.football.model.SeasonTeams;
import java.util.List;

public interface SeasonTeamsMapper {
    List<SeasonTeams> findAll();
    int insert(SeasonTeams e);
}
