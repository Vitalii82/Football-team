package com.football.dao;

import com.football.model.SeasonTeams;
import java.util.List;

public interface SeasonTeamsDao {
    int insert(SeasonTeams e);
    List<SeasonTeams> findAll();
}
