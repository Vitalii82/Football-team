package com.football.service;

import com.football.model.Team;
import java.util.List;

public interface TeamService {
    int create(Team t);
    List<Team> list();
}
