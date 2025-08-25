package com.football.service.impl;

import com.football.dao.TeamDao;
import com.football.dao.impl.TeamDaoJdbc;
import com.football.model.Team;
import com.football.service.TeamService;
import java.util.List;

public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao = new TeamDaoJdbc();

    @Override
    public int create(Team t) { return teamDao.insert(t); }

    @Override
    public List<Team> list() { return teamDao.findAll(); }
}
