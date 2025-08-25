package com.football.service;

import com.football.dao.impl.TeamDaoJdbc;
import com.football.model.Team;
import java.util.List;

public class TeamService {
    private final TeamDaoJdbc dao = new TeamDaoJdbc();
    public int create(Team t) { return dao.insert(t); }
    public List<Team> list() { return dao.findAll(); }
}
