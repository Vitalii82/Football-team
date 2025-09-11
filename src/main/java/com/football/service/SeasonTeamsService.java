package com.football.service;

import com.football.model.SeasonTeams;
import java.util.List;

public interface SeasonTeamsService {
    int create(SeasonTeams e);
    List<SeasonTeams> list();
}
